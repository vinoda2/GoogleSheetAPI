package com.xworkz.event.dto;

import lombok.Data;

@Data
public class EventDTO extends AbstractAuditDTO{
	
	private String eventName;
	private String eventType;
	private Double budget;
	private String day;

}
