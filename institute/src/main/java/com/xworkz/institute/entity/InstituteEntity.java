package com.xworkz.institute.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "new_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
		@NamedQuery(name = "findByName", query = "select en from InstituteEntity en where en.instituteName=:iname"),
		@NamedQuery(name = "findByEmail", query = "select ent from InstituteEntity ent where ent.email=:iemail"),
		@NamedQuery(name = "findAll", query = "select entity from InstituteEntity entity"), 
})
public class InstituteEntity {

	@Id
	@Column(name = "i_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "i_name")
	private String instituteName;
	@Column(name = "i_email")
	private String email;
	@Column(name = "i_contactnumber")
	private String contactNumber;
	@Column(name = "i_password")
	private String password;
	@Column(name = "i_confirmpassword")
	private String confirmPassword;
}
