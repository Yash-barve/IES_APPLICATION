package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.binding.Forgot;
import com.main.binding.LoginForm;
import com.main.binding.UnlockForm;
import com.main.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/unlock")
	public ResponseEntity<String> unlockStatus(@RequestBody UnlockForm form) {
		String success = userService.unlock(form);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginForm form) {
		String status = userService.login(form);
		if (status.contains("success")) {
//			planService.dashboardPage(null);
			return new ResponseEntity<>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/forgot")
	public ResponseEntity<String> forgotPwd(@RequestBody Forgot form) {
		String status = userService.forgot(form);
		return new ResponseEntity<>(status, HttpStatus.OK);

	}
}
