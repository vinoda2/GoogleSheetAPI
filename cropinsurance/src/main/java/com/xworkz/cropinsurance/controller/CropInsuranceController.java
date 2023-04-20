package com.xworkz.cropinsurance.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.log.Log;
import com.xworkz.cropinsurance.dto.CropInsuranceDTO;
import com.xworkz.cropinsurance.service.CropInsuranceService;

@Controller
@RequestMapping("/")
public class CropInsuranceController {

	@Autowired
	private CropInsuranceService cropInsuranceService;

	private List<String> area = Arrays.asList("Bangalore", "Mangalore", "Hubli", "Dharwad", "Shimoga");

	@GetMapping
	public String getArea(Model model) {
		model.addAllAttributes(area);
		return "Insurance";
	}

	public CropInsuranceController() {
		System.out.println("CropInsuranceController");

	}

	@PostMapping("/save")
	public String onSave(CropInsuranceDTO dto, Model model) {
		System.out.println("on save method is running");
		System.out.println(dto);
		Set<ConstraintViolation<CropInsuranceDTO>> violation = this.cropInsuranceService.validateAndSave(dto);
		if(violation.isEmpty()) {
			System.out.println("no violation ");
			return "Success";
		}
		model.addAttribute("areas",area);
		model.addAttribute("errors",violation);
		model.addAttribute("cropdto",dto);
		System.err.println("violations are present");
		return "Insurance";
	}
}
