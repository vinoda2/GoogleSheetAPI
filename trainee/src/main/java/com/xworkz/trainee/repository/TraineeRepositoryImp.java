package com.xworkz.trainee.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.trainee.dto.TraineeDTO;
import com.xworkz.trainee.entity.TraineeEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TraineeRepositoryImp implements TraineeRepository {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	public TraineeRepositoryImp() {
		log.info("TraineeRepositoryImp running");

	}

	@Override
	public boolean onSave(TraineeEntity entity) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(entity);
		transaction.commit();
		manager.close();
		return true;
	}

	@Override
	public List<TraineeEntity> findAll() {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("find");
			List<TraineeEntity> entityList = query.getResultList();
			return entityList;
		} finally {
			manager.close();
		}
	}

	@Override
	public Long findByName(String userName) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			log.info("Repository");
			Query query = manager.createNamedQuery("byName");
			query.setParameter("userBy", userName);
			Object nameCount = query.getSingleResult();
			Long value = (Long) nameCount;
			return value;
		} finally {
			manager.close();
		}
	}

	@Override
	public Long findByEmail(String email) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("ByEmail");
			query.setParameter("byEmail", email);
			Object emailCount = query.getSingleResult();
			Long value = (Long) emailCount;
			return value;
		} finally {
			manager.close();
		}
	}

	public TraineeEntity findName(String userName) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		TraineeEntity entity;
		try {
			Query query = manager.createNamedQuery("userName");
			query.setParameter("userBy",userName);
			entity=(TraineeEntity) query.getSingleResult();
		}finally {
			manager.close();
		}
		return entity;
	}
	@Override
	public boolean loginCount(String traineeName,int count) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction entityTransaction=manager.getTransaction();
			entityTransaction.begin();
			Query  query= manager.createNamedQuery("loginCount");
			query.setParameter("user", traineeName);
			query.setParameter("count",count);
			query.executeUpdate();
			entityTransaction.commit();
			return true;
		}finally {
			manager.close();
		}
	}
}
