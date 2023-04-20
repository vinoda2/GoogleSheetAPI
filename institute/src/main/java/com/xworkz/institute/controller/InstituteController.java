package com.xworkz.institute.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.institute.dto.InstituteDTO;
import com.xworkz.institute.service.InstituteService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class InstituteController {
	@Autowired
	InstituteService instituteService;
	
	public InstituteController() {
		log.info("InstituteController running ----");
	}

	@PostMapping("/register")
	public String onSave(InstituteDTO dto, Model model) {
		log.info("this is onSave method ");
		Set<ConstraintViolation<InstituteDTO>> violation = this.instituteService.validateAndSave(dto);
		//boolean  send= this.instituteService.sendMail(dto.getEmail(), dto, dto.getPassword());
		if (violation != null) {
			log.error("Errors");
			model.addAttribute("errors", violation);
			return "Registration";
		} else {
			model.addAttribute("entity", dto);
			log.info("entity:" + dto);
		
			return "Success";
		}
	}
	@PostMapping("/loginpage")
	public String onLogIn(@RequestParam String email,String password) {
		
		return "LoginSuccess";
	}
	
	

	@PostMapping("/searchId")
	public String onSearch(@RequestParam int id, Model model) {
		log.info("on Search method");
		InstituteDTO dto = this.instituteService.findById(id);
		if (dto != null) {
			model.addAttribute("dtos", dto);
		} else {

			model.addAttribute("message", "Date not found");
		}
		return "SearchCourse";
	}

	@PostMapping("/findbynameemail")
	public String onSearchNameAndEmail(@RequestParam String instituteName, @RequestParam String email, Model model) {
		System.out.println("Search by email is running");
		List<InstituteDTO> dtos = this.instituteService.findByNameAndEmail(instituteName, email);
		if (dtos.size() >= 0) {
			if (dtos != null) {

				model.addAttribute("dtos", dtos);
			} else {
				model.addAttribute("message", "Date not found");
			}
		} else {
			model.addAttribute("message", "Date not found");
		}
		return "FindByNameAndEmail";

	}

	@PostMapping("/searchName")
	public String onSearch(@RequestParam String instituteName, Model model) {
		log.info("on Search method");
		List<InstituteDTO> dtos = this.instituteService.findByName(instituteName);
		log.info("dtos:"+dtos);
		if (dtos != null) {
			model.addAttribute("dto", dtos);
		} else {
			model.addAttribute("message", "Date not found");
		}
		return "SearchByName";
	}

	@PostMapping("/searchByEmail")
	public String searchEmail(@RequestParam String email, Model model) {
		log.info("on Search method");
		InstituteDTO dtos = this.instituteService.findByEmail(email);
		log.info("dtos:"+dtos);
		if (dtos != null) {
			model.addAttribute("dto", dtos);
		} else {
			model.addAttribute("message", "Date not found");
		}
		return "SearchByEmail";
	}

	@PostMapping("/findAll")
	public String onFind(Model model) {
		log.info("Find All method");
		List<InstituteDTO> dtolist = this.instituteService.findAll();
		if (dtolist != null) {
			model.addAttribute("dtolist", dtolist);
		} else {
			model.addAttribute("message", "Date not found");
		}
		return "FindAll";
	}

	@GetMapping("/update")
	public String update(Model model, @RequestParam int id) {
		log.info("Running in update get method");
		InstituteDTO institutedtos = this.instituteService.findById(id);
		model.addAttribute("dtos", institutedtos);
		// model.addAttribute("type", type);
		return "UpdateDetails";
	}

	@PostMapping("/update")
	public String onUpdate(InstituteDTO dto, Model model) {
		log.info("this is on update method");
		Set<ConstraintViolation<InstituteDTO>> violation = this.instituteService.updateAndSave(dto);
		if (violation.size() > 0) {
			model.addAttribute("errors", violation);
		} else {
			model.addAttribute("message", "institute details updated successfully");
		}
		return "UpdateDetails";
	}

	@GetMapping("/delete")
	public String onDelete(Model model, @RequestParam int id) {
		InstituteDTO dto = this.instituteService.onDelete(id);
		model.addAttribute("message", "data deleted");
		return "SearchByName";
	}
	@PostMapping("/sendmail")
	public String onSend(InstituteDTO dto) {
		boolean  send= this.instituteService.sendMail(dto.getEmail(), dto, dto.getPassword());
		System.out.println("mail sent "+send);
		return "Success";
	}
}
