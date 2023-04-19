package com.xworkz.googlesheetconnection.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TraineeDTO {
	@NotBlank
	@NotEmpty
	@Size(min=3)
	private String studentName;
	@Email
	private String email;
	@NotBlank
	@NotEmpty
	@Size(min=10,max=10,message="Contact number must be 10 Digits")
	private String contactNumber;
	@NotBlank
	@NotEmpty
	private String address;
	private Boolean disabled = false;
	
}
