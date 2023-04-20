package com.xworkz.institute.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

public class InstituteDTO {


	private int id;
	@NotBlank
	@NotEmpty
	@Size(min = 3, message = "Institute name should be more then 3 letters")
	@Pattern(regexp = "[^0-9]*", message = "must not contain special characters")
	private String instituteName;
	@NotBlank
	@NotEmpty
	@Email
	private String email;
	private String contactNumber;
	@NotBlank
	@NotEmpty
	@Size(min = 8, max = 16, message = "password should be more then 8 letters and less then 16 letters")
	private String password;
	@NotBlank
	@NotEmpty
	@Size(min = 8, max = 16, message = "password should be more then 8 letters and less then 16 letters")
	private String confirmPassword;

	public InstituteDTO() {
		
	}
	public InstituteDTO(int id,String email,String instituteName,
			String contactNumber,String password,String confirmPassword) {
		super();
		this.id = id;
		this.instituteName = instituteName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "InstituteDTO [id=" + id + ", instituteName=" + instituteName + ", email=" + email + ", contactNumber="
				+ contactNumber + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
}
