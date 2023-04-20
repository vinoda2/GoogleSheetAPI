package com.xworkz.event.resources;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/")
@EnableWebMvc
public class AjaxController {
	public AjaxController() {
		System.out.println("This is Ajax Controller");
	}
	
	@GetMapping(value="/data", produces= MediaType.APPLICATION_JSON_VALUE )
	public String getData() {
		return "this is Ajax controller";
	}
	@GetMapping(value="/countrylist", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getCountry(){
		List<String> countryList =Arrays.asList("India","Pakisthan","Bangladesh","Shrilanka");
		return countryList;
	}

}
