package com.xworkz.cropinsurance.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.cropinsurance.dto.CropInsuranceDTO;
@Repository
public class CropInsuranceRepositoryImp implements CropInsuranceRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public CropInsuranceRepositoryImp() {
		System.out.println("CropInsuranceRepository ");
	}

	@Override
	public boolean saveDTO(CropInsuranceDTO dto) {
		System.out.println("CropInsuranceRepository imp");
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(dto);
		transaction.commit();
		manager.close();
		System.out.println("data saved");
		return false;
	}

}
