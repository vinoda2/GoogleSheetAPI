package com.xworkz.cropinsurance.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.cropinsurance.dto.CropInsuranceDTO;

public interface CropInsuranceService {
	
	public abstract Set<ConstraintViolation<CropInsuranceDTO>> validateAndSave(CropInsuranceDTO dto);

}
