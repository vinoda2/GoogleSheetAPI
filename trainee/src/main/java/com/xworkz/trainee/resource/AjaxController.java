package com.xworkz.trainee.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.trainee.service.TraineeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
@EnableWebMvc
public class AjaxController {
	@Autowired
	TraineeService traineeService;
	@GetMapping(value="/userName/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkUser(@PathVariable String user) {
		log.info("Ajax controller");
		Long userCount=this.traineeService.findByName(user);
		log.info("user count:",userCount);
		if(userCount==0) {
			log.info("User not exists");
			return "";
		}
		return "User Name Exists";
		
	}
	@GetMapping(value="/userEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkEmail(@PathVariable String email) {
		log.info("Ajax controller ");
		Long emailCount=this.traineeService.findByEmail(email);
		if(emailCount==0) {
			return "";
		}
		return "Email Id Exist";
	}

}
