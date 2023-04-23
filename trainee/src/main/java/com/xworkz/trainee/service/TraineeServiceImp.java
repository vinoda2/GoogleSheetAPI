package com.xworkz.trainee.service;

import java.time.LocalTime;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xworkz.trainee.dto.TraineeDTO;
import com.xworkz.trainee.entity.TraineeEntity;
import com.xworkz.trainee.repository.TraineeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TraineeServiceImp implements TraineeService {

	@Autowired
	TraineeRepository traineeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public TraineeServiceImp() {
		log.info("TraineeServiceImp running");
	}

	@Override
	public Set<ConstraintViolation<TraineeDTO>> validateAndSave(TraineeDTO dto) {
		log.info("validate And save method");
		Long nameCount = this.traineeRepository.findByName(dto.getTraineeName());
		Long emailCount = this.traineeRepository.findByEmail(dto.getTraineeEmail());
		log.info("name count:", nameCount, ":=>email count:", emailCount);
		Set<ConstraintViolation<TraineeDTO>> violations = valid(dto);
		if (violations != null && !violations.isEmpty()) {
			log.error("Error exists in DTO");
			return violations;
		}
		if (!dto.getPassword().equals(dto.getConfirmPassword())) {
			return null;
		}
		if (nameCount == 0 && emailCount == 0) {
			TraineeEntity entity = new TraineeEntity();
			log.info(dto.getTraineeEmail());
			//boolean sent = this.sendMail(dto.getTraineeEmail());
			entity.setTraineeName(dto.getTraineeName());
			entity.setTraineeEmail(dto.getTraineeEmail());
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
			entity.setResetPassword(false);
			entity.setPassswordTime(LocalTime.of(0, 0, 0));
			boolean save = this.traineeRepository.onSave(entity);
			log.info("this is repsitory:"+entity);
			//log.info("password:", entity.getPassword());
			//log.info(" email:", save);
		}
		return Collections.emptySet();
	}

	@Override
	public List<TraineeDTO> findAll() {
		List<TraineeEntity> entityList = this.traineeRepository.findAll();
		List<TraineeDTO> dtoList = new ArrayList<TraineeDTO>();
		for (TraineeEntity en : entityList) {
			TraineeDTO dto = new TraineeDTO();
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Long findByName(String userName) {
		log.info("Trainee service findBy name");
		Long nameCount = this.traineeRepository.findByName(userName);
		return nameCount;
	}

	@Override
	public Long findByEmail(String email) {
		Long emailCount = this.traineeRepository.findByEmail(email);
		return emailCount;
	}

	// login
	public TraineeDTO logIn(String traineeName, String password) {
		// log.info("this is service");
		log.info("passing from controller:"+traineeName+password);
		TraineeEntity entity = this.traineeRepository.findName(traineeName);
		TraineeDTO dto = new TraineeDTO();
		BeanUtils.copyProperties(entity, dto);
		if (entity.getLoginCount() >= 3) {
			return dto;
		}
		if (dto.getTraineeName().equals(traineeName) && passwordEncoder.matches(password, entity.getPassword())) {
			return dto;
		}else {
			this.traineeRepository.loginCount(traineeName, entity.getLoginCount() + 1);
			return null;
		}

	}

	public TraineeDTO reSetPassword(String email) {
		String newPassword=null;
		TraineeEntity entity=this.traineeRepository.reSetPassword(email);
		if(entity!=null) {
			entity.setPassword(passwordEncoder.encode(newPassword));
			entity.setLoginCount(0);
			entity.setResetPassword(true);
			entity.setPassswordTime(LocalTime.now().plusSeconds(180));
			//call update method
			boolean update=this.traineeRepository.update(entity);
			if(update) {
				this.sendMail(entity.getTraineeEmail());
			}
			TraineeDTO dto=new TraineeDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}
	
	private Set<ConstraintViolation<TraineeDTO>> valid(TraineeDTO dto) {
		ValidatorFactory valid = Validation.buildDefaultValidatorFactory();
		Validator v = valid.getValidator();
		Set<ConstraintViolation<TraineeDTO>> violation = v.validate(dto);
		return violation;
	}

	public boolean sendMail(String email) {
		String host = "smtp.office365.com";
		final String user = "vinodamallappa@outlook.com";
		final String password1 = "1973@Dad";
		String to = email;
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
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
			Transport.send(message);
			System.out.println("message sent successfully...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
