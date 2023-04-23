package com.xworkz.trainee.controller;

import java.time.LocalTime;
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
			log.info("values from user:"+traineeName+password);
			TraineeDTO dto = this.traineeService.logIn(traineeName, password);
			log.info("this is controller class", dto);
			log.info("login count:" + dto.getLoginCount());
			if (dto.getLoginCount() >= 3) {
				model.addAttribute("message","Account Locked Reset the Password");
				return "Login";
			}
			if(dto!=null) {
				if(dto.getResetPassword()==true) {
					if(!dto.getPasswordTime().isAfter(LocalTime.now())) {
						model.addAttribute("messages","time out please try again");
						return "Success";
					}
					model.addAttribute("traineeName",dto.getTraineeName());
					return "updatepassword";
				}
				model.addAttribute("dto",dto);
				return "LoginSuccess";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("match","user ID or password is not matching");
		return "index";
	}
	
	@PostMapping("/resetpassword")
	public String resetPassword(String email,Model model) {
		try {
			TraineeDTO dto=this.traineeService.reSetPassword(email);
			if(dto.getResetPassword()==true) {
				model.addAttribute("message","passowd reset successfull login");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "resetpassword";
	}
	
}
