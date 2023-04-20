package com.xworkz.event.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xworkz.event.dto.EventDTO;
import com.xworkz.event.service.EventService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/")
@Controller
@Slf4j

public class EventController {

	public EventController() {
		log.info("EventController");
	}
	
	@Autowired
	EventService eventService;
	
	@PostMapping("register")
	public String onSave(EventDTO dto, Model model) {
		Set<ConstraintViolation<EventDTO>> violations = this.eventService.validateAndSave(dto);

		if (violations != null && violations.isEmpty() && dto != null) {
			model.addAttribute("message", "Registration sucessfull");
			log.info("" + dto);
			return "Register";
		}
		model.addAttribute("errors", violations);
		model.addAttribute("messag", "Registration failed");
		model.addAttribute("dto", dto);
		return "Register";

	}

}
