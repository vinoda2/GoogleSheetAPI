package com.xworkz.institute.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="state")
@NamedQuery(name="getState",query="select state from StateEntity state")
public class StateEntity {
	@Column(name="country_id")
	private int countryId;
	@Id
	@Column(name="state_id")
	private int stateID;
	@Column(name="state_name")
	private String stateName;
}
