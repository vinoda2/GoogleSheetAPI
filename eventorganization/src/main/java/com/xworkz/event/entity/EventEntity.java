package com.xworkz.event.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EventEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "e_id")
	private int id;
	@Column(name = "e_eventName")
	private String eventName;
	@Column(name = "e_eventType")
	private String eventType;
	@Column(name = "e_budget")
	private Double budget;
	@Column(name = "e_day")
	private String day;

}
