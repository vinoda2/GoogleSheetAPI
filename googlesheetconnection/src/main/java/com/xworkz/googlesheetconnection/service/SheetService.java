package com.xworkz.googlesheetconnection.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.xworkz.googlesheetconnection.dto.TraineeDTO;
import com.xworkz.googlesheetconnection.util.GoogleSheetUtil;

@Service
@EnableCaching
public class SheetService {

	private static final String APPLICATION_NAME = "Google sheet new ";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	private static final String CREDENTIALS_FILE_PATH = "C:\\Users\\Dell\\Desktop\\New folder\\x-workz\\googlesheetconnection\\src\\main\\resources\\credentials.json";
	private static Sheets sheet;
	String range = "A2:E";
	GoogleCredential credential;

	// loading the connection
	public SheetService() throws IOException, GeneralSecurityException {
		credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH)).createScoped(SCOPES);
		sheet = new Sheets.Builder(new NetHttpTransport(), JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	// inserting data into Google Sheet
	public String writeData(String SheetId, TraineeDTO dto) {
		List<List<Object>> objectlist = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		list.add(dto.getStudentName());
		list.add(dto.getEmail());
		list.add(dto.getContactNumber());
		list.add(dto.getAddress());
		list.add(dto.getDisabled());
		objectlist.add(list);
		ValueRange body = new ValueRange().setValues(objectlist);
		try {
			AppendValuesResponse writeResult = sheet.spreadsheets().values().append(SheetId, range, body)
					.setValueInputOption("Raw").execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Data saved Successfully";

	}
	@Cacheable(value="traineeemail", key="email")
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
		} else {
			System.out.println("Sheet is empty");
		}
		return null;
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
				List<List<Object>> lists = new ArrayList();
				lists.add(updateList);
				ValueRange body = new ValueRange().setValues(lists);
				UpdateValuesResponse updateResponse = sheet.spreadsheets().values().update(sheetId, updateRange, body)
						.setValueInputOption("Raw").execute();
				return "update Successfully done";
			}
			updateIndex++;
		}
		return "update failed";
	}

	// find by Name return list<Object>
	public List<TraineeDTO> findByName(String sheetId, String name) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		List<TraineeDTO> nameList = new ArrayList<TraineeDTO>();

		if (valueList != null && valueList.size() != 0) {
			for (List<Object> list : valueList) {
				if (list.get(0).equals(name)) {
					TraineeDTO dto = new TraineeDTO();
					getDTO(list, dto);
					nameList.add(dto);
				} else {
					System.out.println("Name not present");
				}
			}

		}
		return nameList;
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
				if (list.get(1).equals(email)) {
					getDTO(list, dto);
				}
			}
		}
		return dto;
	}

	// Find by Mobile number it return DTO matching with Mobile number
	public TraineeDTO findByMobileNumber(String sheetId, String mobileNumber) throws IOException {
		List<List<Object>> valueList = readValues(sheetId);
		TraineeDTO dto = new TraineeDTO();
		if (valueList != null && valueList.size() != 0) {
			for (List<Object> list : valueList) {
				if (list.get(2).equals(mobileNumber)) {
					getDTO(list, dto);
				} else {
					System.out.println("Mobile number Not found");
				}
			}
		}
		return dto;
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
						UpdateValuesResponse updateResponse = sheet.spreadsheets().values()
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
			} else {
				System.out.println("account disabled");
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
}
