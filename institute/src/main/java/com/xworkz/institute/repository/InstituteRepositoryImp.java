package com.xworkz.institute.repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.institute.entity.CountryEntity;
import com.xworkz.institute.entity.InstituteEntity;
import com.xworkz.institute.entity.StateEntity;

@Repository
public class InstituteRepositoryImp implements InstituteRepository {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	public InstituteRepositoryImp() {
		System.out.println("this is InstituteServiceImp");
	}


	
	// Save Method
	@Override
	public boolean saveDTO(InstituteEntity entity) {
		System.out.println("this is service method running in InstituteServiceImp");
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		System.out.println(entity);
		transaction.begin();
		manager.persist(entity);
		transaction.commit();
		manager.close();
		return true;

	}

	// find By Id
	@Override
	public InstituteEntity findById(int id) {
		System.out.println("find by ID method");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		InstituteEntity data = entityManager.find(InstituteEntity.class, id);
		entityManager.close();
		return data;
	}

	// find ByName
	@Override
	public List<InstituteEntity> findByName(String instituteName) {
		System.out.println("find by Name method");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("findByName");
		query.setParameter("iname", instituteName);
		List<InstituteEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}
	// find ByEmail
		@Override
		public InstituteEntity findByEmail(String email) {
			System.out.println("find by Name method");
			EntityManager entityManager = this.entityManagerFactory.createEntityManager();
			Query query = entityManager.createNamedQuery("findByEmail");
			query.setParameter("iemail",email);
			InstituteEntity entity = (InstituteEntity)query.getSingleResult();
			entityManager.close();
			return entity;
		}

		//find by name and email
		public List<InstituteEntity> findByNameAndEmail(String instituteName,String email) {
		System.out.println("find by name and email");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("findByNameAndEmail");
		query.setParameter("iname",instituteName);
		query.setParameter("iemail",email);
		List<InstituteEntity> list = query.getResultList();
		return list;
		}
		
	// Find All
	public List<InstituteEntity> findAll() {
		System.out.println("find All method");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("findAll");
		List<InstituteEntity> list = query.getResultList();
		return list;
	}
	
	@Override
	public boolean updateDTO(InstituteEntity entity) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = manager.getTransaction();
			trans.begin();
			manager.merge(entity);
			trans.commit();
			return true;
		} finally {
			manager.close();
		}

	}

	public InstituteEntity onDelete(int id) {
		System.out.println("find by ID method");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		EntityTransaction trans = entityManager.getTransaction();
		InstituteEntity data = entityManager.find(InstituteEntity.class, id);
		if (data != null) {
			trans.begin();
			entityManager.remove(data);
			trans.commit();
			entityManager.close();
			return data;
		}
		return InstituteRepository.super.onDelete(id);

	}

}
