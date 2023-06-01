package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.constants.Appconstants;
import com.main.entity.AdminEntity;
import com.main.exception.ResourceNotFoundException;
import com.main.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody AdminEntity entity) {
		String status = adminService.upsert(entity);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<AdminEntity>> getAllUser() {
		List<AdminEntity> allRecords = adminService.getAllRecords();
		return new ResponseEntity<>(allRecords, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<AdminEntity> getOneUser(@PathVariable Integer id) {
		AdminEntity getDetails = adminService.getById(id);
		return new ResponseEntity<>(getDetails, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteUser(@PathVariable Integer id) {
		String status = adminService.deleteById(id);
		if (status.isEmpty()) {
			throw new ResourceNotFoundException(Appconstants.KEY_INCOR);
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
