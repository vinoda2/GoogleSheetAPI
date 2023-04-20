package com.xworkz.institute.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.institute.dto.InstituteDTO;

@RestController
@RequestMapping("/")
@EnableWebMvc
public class AjaxController {

	//this method display's simple string
	@GetMapping(value="/method", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMethod() {
		return "hello first api done";
	}
	
	@GetMapping(value="/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public InstituteDTO getDTO() {
		InstituteDTO dto = new InstituteDTO();
		dto.setId(2);
		dto.setEmail("vinoda@gmail.com");
		return dto;
	}
	@GetMapping(value="/countrylist", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getCountry(){
		List<String> countryList =Arrays.asList("India","Pakisthan","Bangladesh","Shrilanka");
		return countryList;
	}
	
	@GetMapping(value="/statelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,List<String>> getState(){
		List<String> indiaState =Arrays.asList("karnataka","Andrapradesh","Kerala","Telangana");
		List<String> shrilankaState =Arrays.asList("Anuradhapura","Badulla","Batticaloa","Colombo");
		
		Map<String,List<String>> stateMap=new HashMap<String,List<String>>();
		stateMap.put("India",indiaState);
		stateMap.put("Shrilanka", shrilankaState);
		return stateMap;
	}
}

