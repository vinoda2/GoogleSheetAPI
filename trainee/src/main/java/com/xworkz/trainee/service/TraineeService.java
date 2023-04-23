package com.xworkz.trainee.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.trainee.dto.TraineeDTO;
import com.xworkz.trainee.entity.TraineeEntity;

public interface TraineeService {
	Set<ConstraintViolation<TraineeDTO>> validateAndSave(TraineeDTO dto);
	default List<TraineeDTO> findAll() {
		return Collections.emptyList();
	}

	default Long findByName(String userName) {
		return null;
	}

	default Long findByEmail(String email) {
		return null;
	}
	default TraineeDTO logIn(String traineeName,String password) {
		return null;
	}
	default TraineeDTO reSetPassword(String email) {
		return null;
	}

	default TraineeDTO updatePassword(String userId, String password, String confirmPassword) {
		return null;
	}
}
