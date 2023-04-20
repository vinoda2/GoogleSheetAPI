package com.xworkz.event.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.event.dto.EventDTO;
import com.xworkz.event.entity.EventEntity;
import com.xworkz.event.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventServiceImp implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	private Set<ConstraintViolation<EventDTO>> validate(EventDTO dto) {
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validationFactory.getValidator();
		Set<ConstraintViolation<EventDTO>> vailation = validator.validate(dto);
		return vailation;
	}


	@Override
	public Set<ConstraintViolation<EventDTO>> validateAndSave(EventDTO dto) {
		Set<ConstraintViolation<EventDTO>> violations = validate(dto);
		if (violations != null && !violations.isEmpty()) {
			log.info("there is Violation in dto");
			return violations;
		}
		EventEntity eventEntity = new EventEntity();
		BeanUtils.copyProperties(dto, eventEntity);
		boolean saved =this.eventRepository.save(null);
		log.info("Saved in Entity-" + saved);
		return null;
	}

	@Override
	public boolean sendMail(String email, String text) {
		
		return false;
	}

}
