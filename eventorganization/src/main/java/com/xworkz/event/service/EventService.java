package com.xworkz.event.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import com.xworkz.event.dto.EventDTO;

public interface EventService {
	Set<ConstraintViolation<EventDTO>> validateAndSave(EventDTO dto);
	
	default EventDTO signIn(String userId, String password) {
		return null;
	}

	default List<EventDTO> findAll() {
		return Collections.emptyList();
	}

	default Long findByEmail(String email) {
		return null;
	}

	default Long findByMobile(Long mobile) {
		return null;
	}

	default Long findByUser(String user) {
		return null;
	}
	default EventDTO reSetPassword(String email) {
		return null;
	}
	default EventDTO updatePassword(String userId, String password,String confirmPassword) {
		return null;
	}
	
	boolean sendMail(String email,String text);
	
}
