package com.xworkz.googlesheetconnection.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.googlesheetconnection.dto.TraineeDTO;
import com.xworkz.googlesheetconnection.service.SheetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trainees")
@CrossOrigin(origins = "http://localhost:3000")
public class AutoSuggestionController {
	
	@Autowired
	SheetService sheetService;
	
	@GetMapping("/search/{searchText}")
	public List<TraineeDTO> findByMobile(@RequestHeader String sheetId, @PathVariable String searchText)
			throws IOException {
		return this.sheetService.findByMobile(sheetId, searchText);
	}

}
