package com.xworkz.trainee.repository;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import com.xworkz.trainee.dto.TraineeDTO;
import com.xworkz.trainee.entity.TraineeEntity;

public interface TraineeRepository {
	abstract boolean onSave(TraineeEntity entity);

	default List<TraineeEntity> findAll() {
		return Collections.emptyList();
	}

	default Long findByName(String userName) {
		return null;
	}

	default Long findByEmail(String email) {
		return null;
	}
	default TraineeEntity findName(String userName) {
		return null;
	}
	boolean loginCount(String userId, int count);
	default TraineeEntity reSetPassword(String email) {
		return null;
	}
	//boolean update(TraineeEntity TraineeEntity);
	boolean updatePassword(String userName, String password, Boolean resetPassword, LocalTime passwordTime);

	abstract boolean update(TraineeEntity entity);
}
