package com.xworkz.cropinsurance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="insuranceDetails")
public class CropInsuranceEntity {
	@Id
	int id;
	private String cropName;
	private String cropType;
	private String insuranceType;
	private String companyName;


}
