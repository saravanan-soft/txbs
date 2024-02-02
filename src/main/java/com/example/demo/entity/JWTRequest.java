package com.example.demo.entity;

public class JWTRequest {
	
	private String username;
	private String password;
	
	public JWTRequest(String userName,String password) {
		this.username=userName;
		this.password=password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
