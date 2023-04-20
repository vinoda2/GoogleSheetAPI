package com.xworkz.event.repository;

import java.util.Collections;
import java.util.List;

import com.xworkz.event.entity.EventEntity;

public interface EventRepository {
	boolean save(EventEntity eventEntity);

	default List<EventEntity> findAll() {
		return Collections.emptyList();
	}

}
