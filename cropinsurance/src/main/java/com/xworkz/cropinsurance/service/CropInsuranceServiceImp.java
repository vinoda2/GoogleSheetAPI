package com.xworkz.cropinsurance.service;


import java.util.Collections;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xworkz.cropinsurance.dto.CropInsuranceDTO;
import com.xworkz.cropinsurance.repository.CropInsuranceRepository;

@Service
public class CropInsuranceServiceImp implements CropInsuranceService {
	@Autowired
	private CropInsuranceRepository cropInsuranceRepository;

	@Override
	public Set<ConstraintViolation<CropInsuranceDTO>> validateAndSave(CropInsuranceDTO dto) {
	
	ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
	Validator validator=factory.getValidator();
	Set<ConstraintViolation<CropInsuranceDTO>> violations=validator.validate(dto);
	if(!violations.isEmpty()) {
		System.out.println("error exists");
		return violations;
	}else {
		System.out.println("you can save data");
		boolean save=this.cropInsuranceRepository.saveDTO(dto);
		return violations;
	}
	
	}

}
