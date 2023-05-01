package com.xworkz.googlesheetconnection.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xworkz.googlesheetconnection.dto.TraineeDTO;
import com.xworkz.googlesheetconnection.service.SheetService;

@RestController
@RequestMapping("/trainee")
@CrossOrigin(origins = "http://localhost:3000")
public class SheetController {
	@Autowired
	SheetService sheetService;

	@PostMapping()
	public String onSave(@RequestHeader String sheetId,@Valid @RequestBody TraineeDTO dto) throws IOException {
		return this.sheetService.writeData(sheetId, dto);
	}
	
	@GetMapping("/alldata")
	public List<TraineeDTO> getData(@RequestHeader String sheetId) throws IOException {
		return this.sheetService.getValues(sheetId);
	}

	@PutMapping()
	public String updateBydto(@RequestBody TraineeDTO dto, @RequestHeader String sheetId) throws IOException {
		Log.info("data:"+dto);
		return this.sheetService.listUpdate(sheetId, dto);
	}

	@GetMapping("/byname/{name}")
	public TraineeDTO findByName(@RequestHeader String sheetId, @PathVariable String name) throws IOException {
		return this.sheetService.findByName(sheetId, name);
	}

	@GetMapping("/byaddress/{address}")
	public List<TraineeDTO> findByAddress(@RequestHeader String sheetId, @PathVariable String address)
			throws IOException {
		return this.sheetService.findByAddress(sheetId, address);
	}

	@GetMapping("/email/{email}")
	public TraineeDTO findByEmail(@RequestHeader String sheetId,@PathVariable String email) throws IOException {
		return this.sheetService.findByEmail(sheetId, email);
	}

	@GetMapping("/contact/{mobileNumber}")
	public List<TraineeDTO> findByMobileNumber(@RequestHeader String sheetId, @PathVariable String mobileNumber)
			throws IOException {
		return this.sheetService.findByMobile(sheetId, mobileNumber);
	}

	@GetMapping()
	public List<TraineeDTO> findByEnabled(@RequestHeader String sheetId) throws IOException {
		return this.sheetService.findByEnabled(sheetId);
	}

	@DeleteMapping("/{email}")
	public String updateDisableByEmail(@RequestHeader String sheetId, @PathVariable String email) throws IOException {
		return this.sheetService.updateDisableByEmail(sheetId, email);
	}
	@GetMapping("/location")
	public List<String> getAddress(@RequestHeader String sheetId) throws IOException{
		return this.sheetService.getLocation(sheetId);
	}
}
