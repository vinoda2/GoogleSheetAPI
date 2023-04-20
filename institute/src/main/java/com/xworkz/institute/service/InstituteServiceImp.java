package com.xworkz.institute.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.institute.dto.InstituteDTO;
import com.xworkz.institute.entity.InstituteEntity;
import com.xworkz.institute.repository.InstituteRepository;

@Service
public class InstituteServiceImp implements InstituteService {
	@Autowired
	InstituteRepository instituteRepository;

	public InstituteServiceImp() {
		System.out.println("this is InstituteServiceImp");
	}

	// Save method
	@Override
	public Set<ConstraintViolation<InstituteDTO>> validateAndSave(InstituteDTO dto) {
		System.out.println("this is validate method in  service");
		Set<ConstraintViolation<InstituteDTO>> violation = valid(dto);
		if (violation != null && !violation.isEmpty()) {
			System.err.println("Constraints exists in dto");
			return violation;
		} else {
			System.out.println("violation are not there");
			//InstituteEntity entity = new InstituteEntity();
			//BeanUtils.copyProperties(dto, entity);
			
			boolean save= this.instituteRepository.saveDTO(InstituteEntity.builder().confirmPassword(dto.getConfirmPassword()).build());
			System.out.println(save);
			return Collections.emptySet();

		}
		

	}

	// find by Id method
	public InstituteDTO findById(int id) {
		if (id > 0) {
			InstituteEntity entity = this.instituteRepository.findById(id);
			if (entity != null) {
				InstituteDTO dto = new InstituteDTO();
				BeanUtils.copyProperties(entity, dto);
				return dto;
			}
		}
		return null;
	}
	//find by name method
	public List<InstituteDTO> findByName(String instituteName) {
		if (instituteName != null)
			try {
				List<InstituteDTO> list = new ArrayList<>();
				List<InstituteEntity> entities = this.instituteRepository.findByName(instituteName);
				for (InstituteEntity entity : entities) {
					InstituteDTO dto = new InstituteDTO();
					BeanUtils.copyProperties(entity, dto);
					list.add(dto);
				}
				System.out.println("list count:" + list.size());
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	//find by email method
	public InstituteDTO findByEmail(String email) {
		if (email != null) {
				InstituteEntity entity = this.instituteRepository.findByEmail(email);
					InstituteDTO dto = new InstituteDTO();
					BeanUtils.copyProperties(entity, dto);
					return dto;
				}else {
					return null;
				}
	}
	//find by name and email
	public List<InstituteEntity> findByNameAndEmail(String instituteName,String email) {
		if(instituteName!=null && email!=null) {
			List<InstituteEntity> entity = this.instituteRepository.findByNameAndEmail(instituteName, email);
			InstituteDTO dto = new InstituteDTO();
			BeanUtils.copyProperties(entity, dto);
			return entity;
		}
		return null;
	}
	//find All
	public List<InstituteDTO> findAll() {
				List<InstituteDTO> list = new ArrayList<>();
				List<InstituteEntity> entities = this.instituteRepository.findAll();
				for (InstituteEntity entity : entities) {
					InstituteDTO dto = new InstituteDTO();
					BeanUtils.copyProperties(entity, dto);
					list.add(dto);
				}
				System.out.println("list count:" + list.size());
				return list;
	}	
	//Update method
	@Override
	public Set<ConstraintViolation<InstituteDTO>> updateAndSave(InstituteDTO dto) {
		ValidatorFactory valid = Validation.buildDefaultValidatorFactory();
		Validator v = valid.getValidator();
		Set<ConstraintViolation<InstituteDTO>> violation = v.validate(dto);
		if (violation != null && !violation.isEmpty()) {
			System.err.println("Constraints exists in dto");
			return violation;
		}else {
			InstituteEntity institute = new InstituteEntity();
			BeanUtils.copyProperties(dto, institute);
			boolean update= this.instituteRepository.updateDTO(institute);
			System.out.println(update);
			return Collections.emptySet();
		}

	}

	// delete method
	@Override
	public InstituteDTO onDelete(int id) {
		InstituteEntity entity = this.instituteRepository.onDelete(id);
		if (entity != null) {
			InstituteDTO dto = new InstituteDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return InstituteService.super.onDelete(id);
	}

	// validate method
	private Set<ConstraintViolation<InstituteDTO>> valid(InstituteDTO dto) {
		ValidatorFactory valid = Validation.buildDefaultValidatorFactory();
		Validator v = valid.getValidator();
		Set<ConstraintViolation<InstituteDTO>> violation = v.validate(dto);
		return violation;
	}
	
	@Override
	public boolean sendMail(String email, InstituteDTO entity, String password) {

		String host = "mail.smtp.host";
		final String user = "vinodamallappa73@gmail.com";
		final String password1 = "xworkz@123";
		String to = email;

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.transport.protocol", "smtp");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password1);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Registration Confirmation  Mail");
			message.setText("Thanks for registering");

			// send the message
			Transport.send(message);

			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}
}
