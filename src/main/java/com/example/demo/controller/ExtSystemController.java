package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ExtSystems;

@RestController
@RequestMapping("/api/v1")
public class ExtSystemController {
	
	@PostMapping("/saveextsystems")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> saveExtSystems(@RequestBody ExtSystems extsystems){
		
		return new ResponseEntity<>("ok",new HttpHeaders(),HttpStatus.ACCEPTED);
		
	}

}
