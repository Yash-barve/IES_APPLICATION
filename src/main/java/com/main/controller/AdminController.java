package com.main.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.constants.Appconstants;
import com.main.entity.AdminEntity;
import com.main.entity.RolesEntity;
import com.main.entity.RolesManagerEntity;
import com.main.exception.ResourceNotFoundException;
import com.main.service.AdminService;

@RestController
@RequestMapping("/user")
public class AdminController {

	@Autowired
	
	private AdminService adminService;

	@PostMapping("/")
	public ResponseEntity<String> createUser(@RequestBody AdminEntity entity) {
		
		Set<RolesManagerEntity> roles = new HashSet<>();
		
		RolesEntity role = new RolesEntity();
		role.setRolename("CASEWORKER");
		
		RolesManagerEntity userRole = new RolesManagerEntity();
		userRole.setUser(entity);
		userRole.setRoles(role);	
		
		roles.add(userRole);
		
		String status = adminService.upsert(entity, roles);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<AdminEntity>> getAllUser() {
		List<AdminEntity> allRecords = adminService.getAllRecords();
		return new ResponseEntity<>(allRecords, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminEntity> getOneUser(@PathVariable Integer id) {
		AdminEntity getDetails = adminService.getById(id);
		return new ResponseEntity<>(getDetails, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> DeleteUser(@PathVariable Integer id) {
		String status = adminService.deleteById(id);
		if (status.isEmpty()) {
			throw new ResourceNotFoundException(Appconstants.KEY_INCOR);
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

//	@PutMapping("/update/{id}")
//	public ResponseEntity<?> updatePlan(@PathVariable Integer id, @RequestBody AdminEntity entity) {
//		try {
//			AdminEntity Id = adminService.getById(id);
//			String status = adminService.upsert(entity);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (NoSuchElementException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//	}
}