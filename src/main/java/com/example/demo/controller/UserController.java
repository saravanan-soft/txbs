package com.example.demo.controller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BankUsers;
import com.example.demo.service.UserService;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	private UserService userService;
	  private final PasswordEncoder passwordEncoder;
	
	public UserController(UserService userService,PasswordEncoder passwordEncoder) {
		this.userService=userService;
		this.passwordEncoder=passwordEncoder;
	}
	
	
	@PostMapping("/saveusers")
	public ResponseEntity<Object> saveUsers(@RequestBody  @Valid BankUsers users){
		
		return new ResponseEntity<>(this.userService.saveUsers(users),new HttpHeaders(),HttpStatus.ACCEPTED);
	}
	
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
	        List<String> errors = ex.getBindingResult().getFieldErrors()
	                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
	        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	    }

	    private Map<String, List<String>> getErrorsMap(List<String> errors) {
	        Map<String, List<String>> errorResponse = new HashMap<>();
	        errorResponse.put("errors", errors);
	        return errorResponse;
	    }

	    
	   
}
