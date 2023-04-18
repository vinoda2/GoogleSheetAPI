package com.xworkz.googlesheetconnection.dto;

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
	
	private String studentName;
	private String email;
	private String contactNumber;
	private String address;
	private Boolean disabled = false;

}
