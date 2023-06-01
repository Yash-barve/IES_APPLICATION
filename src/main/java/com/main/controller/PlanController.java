package com.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.PlansEntity;
import com.main.service.PlanService;

@RestController
public class PlanController {

	@Autowired
	private PlanService planService;

	@PostMapping("/createplan")
	public ResponseEntity<String> createPlan(@RequestBody PlansEntity entity) {
		String success = planService.upsertPlan(entity);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	@GetMapping("/getplans")
	public ResponseEntity<List<PlansEntity>> getAllPlans() {
		List<PlansEntity> allPlan = planService.getAllPlan();
		return new ResponseEntity<List<PlansEntity>>(allPlan, HttpStatus.OK);
	}

	@GetMapping("/getplan/{id}")
	public ResponseEntity<PlansEntity> getOnePLan(@PathVariable Integer id) {
		try {
			PlansEntity byId = planService.getById(id);
			return new ResponseEntity<>(byId, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteplan/{id}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer id) {
		try {
			String success = planService.delete(id);
			return new ResponseEntity<>(success, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePlan(@PathVariable Integer id, @RequestBody PlansEntity entity) {
		try {
			PlansEntity existId = planService.getById(id);
			planService.upsertPlan(entity);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
