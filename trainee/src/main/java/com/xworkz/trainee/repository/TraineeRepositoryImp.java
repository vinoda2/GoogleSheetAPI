package com.xworkz.trainee.repository;

import java.time.LocalTime;
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
		TraineeEntity entity = null;
//		log.info("this is findBy name running:"+entity);
		log.info("passing from service:" + userName);
		try {
			Query query = manager.createNamedQuery("byUserName");
			query.setParameter("userBy", userName);
			entity =(TraineeEntity) query.getSingleResult();
			log.info("this is findName method result:"+entity);
			return entity;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean loginCount(String traineeName, int count) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			Query query = manager.createNamedQuery("loginNumber");
			query.setParameter("user", traineeName);
			query.setParameter("number", count);
			query.executeUpdate();
			entityTransaction.commit();
			return true;
		} finally {
			manager.close();
		}
	}
	
	public TraineeEntity reSetPassword(String email) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query=manager.createNamedQuery("emailId");
			query.setParameter("mails",email);
			TraineeEntity entity=(TraineeEntity) query.getSingleResult();
			return entity;
		}finally {
			manager.close();
		}
	}

	@Override
	public boolean updatePassword(String userName, String password, Boolean resetPassword, LocalTime passwordTime) {
		
		return false;
	}

	@Override
	public boolean update(TraineeEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
