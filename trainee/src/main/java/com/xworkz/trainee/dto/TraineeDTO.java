package com.xworkz.trainee.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.xworkz.trainee.entity.TraineeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TraineeDTO {
	
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min=3,max=20)
	private String traineeName;
	@NotBlank
	@NotEmpty
	@NotNull
	@Email
	private String traineeEmail;
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min=8,max=8)
	private String password;
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min=8,max=8)
	private String confirmPassword;
	private int loginCount;
	
}
