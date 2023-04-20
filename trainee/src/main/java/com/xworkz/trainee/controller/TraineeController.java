package com.xworkz.trainee.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xworkz.trainee.dto.TraineeDTO;
import com.xworkz.trainee.service.TraineeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class TraineeController {

	@Autowired
	TraineeService traineeService;

	public TraineeController() {
		log.info("this is Trainee Controller");
	}

	@PostMapping("/register")
	public String onSave(Model model, TraineeDTO dto) {
		log.info("onSave method");
		log.info("Trainee Data:", dto);

		Set<ConstraintViolation<TraineeDTO>> violation = this.traineeService.validateAndSave(dto);
		if (violation.isEmpty() && violation != null && dto != null) {
			model.addAttribute("dto", dto);
			log.info("No error ");
			return "Success";
		} else {
			model.addAttribute("errors", violation);
			log.error("dto contains violations");
		}
		return "index";
	}

	@PostMapping("/login")
	public String onLongin(String traineeName, String password, Model model) {
		try {
			TraineeDTO dto = this.traineeService.logIn(traineeName, password);
			log.info("this is controller class", dto);
			log.info("login count:" + dto.getLoginCount());
			if (dto.getLoginCount() >= 3) {
				model.addAttribute("message", "Account Locked Reset the Password");
				return "index";
			}
			if(dto!=null) {
				return "Success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
}
