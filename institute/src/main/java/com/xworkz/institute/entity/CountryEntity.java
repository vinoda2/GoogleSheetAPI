package com.xworkz.institute.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="country")
@NamedQuery(query="select country from CountryEntity country", name = "getCountry")
public class CountryEntity {
	@Id
	@Column(name="country_id")
	private int country_id;
	@Column(name="country_name")
	private String country_name;
}
