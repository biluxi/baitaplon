package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Account;
import com.app.service.Accountservice;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class AuthController {
	 @Autowired
	    private Accountservice accountService;

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody Account loginRequest) {
	        // Thực hiện kiểm tra tên người dùng và mật khẩu ở đây
	        if (accountService.isValidLogin(loginRequest.getUsername(), loginRequest.getPasword())) {
	        	return new ResponseEntity<>("{\"message\":\"Login successful\"}", HttpStatus.OK);
	        } else {
	        	return new ResponseEntity<>("{\"message\":\"Invalid credentials\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
