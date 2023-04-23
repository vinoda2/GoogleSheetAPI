package com.xworkz.trainee.entity;

import java.time.LocalTime;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@Table(name="trainee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
@NamedQuery(name="findAll", query="select trainee from TraineeEntity trainee"),
@NamedQuery(name = "byName",query = "select count(*) from  TraineeEntity tn where tn.traineeName=:userBy"),
@NamedQuery(name = "ByEmail",query = "select count(*) from  TraineeEntity tn where tn.traineeEmail=:byEmail"),
@NamedQuery(name = "mails",query = "select tn from  TraineeEntity tn where tn.traineeEmail=:byemail"),
@NamedQuery(name = "byUserName",query = "select tn from  TraineeEntity tn where tn.traineeName=:userBy"),
@NamedQuery(name="loginNumber",query="update TraineeEntity tn set tn.loginCount=:number where tn.traineeName=:user")

})
public class TraineeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="t_id")
	private int id;
	@Column(name="t_traineename")
	private String traineeName;
	@Column(name="t_email")
	private String traineeEmail;
	@Column(name="t_password")
	private String password;
	@Column(name="t_logincount")
	private int loginCount;
	@Column(name="t_resetpassword")
	private Boolean resetPassword;
	@Column(name="t_passwordTime")
	private LocalTime passswordTime;
	//private String profileName;
	
}
