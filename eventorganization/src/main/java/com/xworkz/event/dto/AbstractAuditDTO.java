package com.xworkz.event.dto;

import java.time.LocalDateTime;

public abstract class AbstractAuditDTO {
	private String createdBy;
	private LocalDateTime createdDate;
	private String updatedBy;
	private LocalDateTime updatedDate;
}
