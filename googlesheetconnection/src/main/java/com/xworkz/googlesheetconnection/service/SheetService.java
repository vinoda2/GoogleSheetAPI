package com.xworkz.googlesheetconnection.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.xworkz.googlesheetconnection.dto.TraineeDTO;
import com.xworkz.googlesheetconnection.util.GoogleSheetUtil;


@Service
public class SheetService {
	@Autowired
	JavaMailSender mailSender;
	
	private static final String APPLICATION_NAME = "Google sheet new ";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	//private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.json";
//	private static final String CREDENTIALS_FILE_PATH = "classpath:credentials.json";
//	File file = new ClassPathResource("classpath:credentials.json").getFile().getAbsolutePath();
	private static Sheets sheet;
	String range = "A2:E";
	GoogleCredential credential;

	// loading the connection
	public SheetService() throws IOException, GeneralSecurityException {
		credential = GoogleCredential.fromStream(new FileInputStream(new ClassPathResource("credentials.json").getFile().getAbsolutePath())).createScoped(SCOPES);
		sheet = new Sheets.Builder(new NetHttpTransport(), JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	// inserting data into Google Sheet
	public String writeData(String SheetId, TraineeDTO dto) {
		List<List<Object>> objectlist = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		boolean sendMessage=this.sendMail(dto.getEmail(), "X-workz","Thanks for registering");
		list.add(dto.getStudentName());
		list.add(dto.getEmail());
		list.add(dto.getContactNumber());
		list.add(dto.getAddress());
		list.add(dto.getDisabled());
		objectlist.add(list);
		ValueRange body = new ValueRange().setValues(objectlist);
		try {
			sheet.spreadsheets().values().append(SheetId, range, body)
					.setValueInputOption("Raw").execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Data saved Successfully";

	}
//	@Cacheable(value="traineeemail", key="email")
	// read single value from Google Sheet
	public List<TraineeDTO> getValues(String sheetId) throws IOException {
		List<List<Object>> list = readValues(sheetId);
		List<TraineeDTO> sdto = new ArrayList<TraineeDTO>();
		if (list != null && list.size() != 0) {
			for (List<Object> value : list) {
				TraineeDTO dto = new TraineeDTO();
				getDTO(value, dto);
				String status = (String) value.get(4);
				Boolean statusconvert = Boolean.valueOf(status);
				dto.setDisabled(statusconvert);
				sdto.add(dto);
			}

			return sdto;
		} 
		return null;
	}
	
	public List<String> getLocation(String sheetId) throws IOException {
		sheetId="1Np5h34vhqj4W2UithqIbNucnJ97url9IcUwyx2OmG50";
		List<List<Object>> list = readValues(sheetId);
		List<String> locationList=new ArrayList<String>();
		if(list!=null&&list.size()!=0) {
			for(List<Object> value:list) {
				String location =(String) value.get(1);
				locationList.add(location);
			}
		}
		return locationList;
	}

	// update by properties
	public String listUpdate(String sheetId, TraineeDTO dto) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		int updateIndex = 2;
		for (List<Object> list : valueList) {
			if (list.get(1).equals(dto.getEmail())) {
				String columnLetter = GoogleSheetUtil.getColumnLetter(1) + updateIndex;
				String updateRange = columnLetter + ":D";
				List<Object> updateList = new ArrayList<Object>();
				updateList.add(dto.getStudentName());
				updateList.add(dto.getEmail());
				updateList.add(dto.getContactNumber());
				updateList.add(dto.getAddress());
				List<List<Object>> lists = new ArrayList<List<Object>>();
				lists.add(updateList);
				ValueRange body = new ValueRange().setValues(lists);
				sheet.spreadsheets().values().update(sheetId, updateRange, body)
						.setValueInputOption("Raw").execute();
				return "update Successfully done";
			}
			updateIndex++;
		}
		return "update failed";
	}

	// find by Name return list<Object>
	public TraineeDTO findByName(String sheetId, String name) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		//TraineeDTO nameList = new TraineeDTO();
		if (valueList != null && valueList.size() != 0) {
			for (List<Object> list : valueList) {
				String disableStatus = (String) list.get(4);
				Boolean activeStatus = Boolean.valueOf(disableStatus);
				if (list.get(0).equals(name)&&activeStatus.equals(false)) {
					TraineeDTO dto = new TraineeDTO();
					getDTO(list, dto);
					return dto;
	
				}
			}

		}
		return null;
	}

	// Find By Address return List<Object>
	public List<TraineeDTO> findByAddress(String sheetId, String address) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		List<TraineeDTO> addressList = new ArrayList<TraineeDTO>();
		if (valueList != null && valueList.size() != 0) {
			for (List<Object> list : valueList) {
				if (list.get(3).equals(address)) {
					TraineeDTO dto = new TraineeDTO();
					getDTO(list, dto);
					addressList.add(dto);
				}
			}
		}
		return addressList;
	}

	// Find By Email it returns DTO matching with Email
	public TraineeDTO findByEmail(String sheetId, String email) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		TraineeDTO dto = new TraineeDTO();
		if (valueList != null && valueList.size() != 0) {
			for (List<Object> list : valueList) {
				String disableStatus = (String) list.get(4);
				Boolean activeStatus = Boolean.valueOf(disableStatus);
				if (list.get(1).equals(email)&&activeStatus.equals(false)) {
					getDTO(list, dto);
					return dto;
				}
			}
		}
		return null;
	}

	// Find by Mobile number it return DTO matching with Mobile number
	public TraineeDTO findByMobileNumber(String sheetId, String mobileNumber) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		TraineeDTO dto = new TraineeDTO();
		if (valueList != null && valueList.size() != 0) {
			for (List<Object> list : valueList) {
				String disableStatus = (String) list.get(4);
				Boolean activeStatus = Boolean.valueOf(disableStatus);
				if (list.get(2).toString().equals(mobileNumber)&& activeStatus.equals(false)) {
					getDTO(list, dto);
				} 
			}
		}
		return dto;
	}

	// Find by Mobile number it return DTO matching with Mobile number
	public List<TraineeDTO> findByMobile(String sheetId, String searchText) throws IOException {
		List<List<Object>> list = readValues(sheetId);
		List<TraineeDTO> traineeList = new ArrayList<TraineeDTO>();	
		if(list!=null && list.size()!=0) {
			for(List<Object> value:list) {
				String name=(String)value.get(0);
				String email=(String)value.get(1);
				String number=(String) value.get(2);
				String address=(String)value.get(3);
				if(number.contains(searchText)||name.contains(searchText)||email.contains(searchText)||address.contains(searchText)) {
					TraineeDTO dto = new TraineeDTO();
					getDTO(value, dto);
					traineeList.add(dto);
				}
			}
		}
		return traineeList;
	}
	// updateDisableByEmail
	public String updateDisableByEmail(String sheetId, String email) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		int updateIndex = 2;
		if (valueList != null && valueList.size() != 0) {
			for (List<Object> list : valueList) {
				String updateRange = GoogleSheetUtil.getColumnLetter(5) + updateIndex;
				if (list.get(1).equals(email)) {
					Object disableStatus = list.get(4);
					if (!disableStatus.equals("TRUE")) {
						disableStatus = "TRUE";
						List<Object> objlist = new ArrayList<Object>();
						objlist.add(disableStatus);
						List<List<Object>> updateList = new ArrayList<>();
						updateList.add(objlist);
						ValueRange body = new ValueRange().setValues(updateList);
						sheet.spreadsheets().values()
								.update(sheetId, updateRange, body).setValueInputOption("Raw").execute();
					} else {
						return "Account Already disabled";
					}
				}
				updateIndex++;
			}
		} else {
			return "Data not Exists";
		}
		return "Successfully Disabled";
	}

	// find By active students
	public List<TraineeDTO> findByEnabled(String sheetId) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		List<TraineeDTO> activeStudent = new ArrayList<TraineeDTO>();
		for (List<Object> list : valueList) {
			String disableStatus = (String) list.get(4);
			Boolean activeStatus = Boolean.valueOf(disableStatus);
			if (!activeStatus.equals(true)) {
				TraineeDTO dto = new TraineeDTO();
				getDTO(list, dto);
				activeStudent.add(dto);
			}
		}
		return activeStudent;
	}

	
	private void getDTO(List<Object> list, TraineeDTO dto) {
		dto.setStudentName((String) list.get(0));
		dto.setEmail((String) list.get(1));
		dto.setContactNumber((String) list.get(2));
		dto.setAddress((String) list.get(3));
	}

	private List<List<Object>> readValues(String sheetId) throws IOException {
		ValueRange result = sheet.spreadsheets().values().get(sheetId, range).execute();
		List<List<Object>> valueList = result.getValues();
		return valueList;
	}
	public boolean sendMail(String email, String subject, String body) {
		//SimpleMailMessage message = new SimpleMailMessage();
		SimpleMailMessage helper=new SimpleMailMessage();
		helper.setFrom("vinodamallappa@outlook.com");
		helper.setReplyTo("vinodamallappa@outlook.com");
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(body);
		System.out.println(helper);
		mailSender.send(helper);
		return true;
	}
}
