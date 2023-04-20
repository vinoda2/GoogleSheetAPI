package com.xworkz.event.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.event.entity.EventEntity;

import lombok.extern.slf4j.Slf4j;
@Repository
@Slf4j
public class EventRepositoryImpl {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public boolean save(EventEntity eventEntity) {
		log.info("resitory running");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction et = entityManager.getTransaction();
			et.begin();
			entityManager.persist(eventEntity);
			et.commit();
			return true;
		} finally {
			entityManager.close();
		}

	}

}
