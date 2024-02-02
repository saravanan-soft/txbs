package com.example.demo.entity;

public class JWTResponse {
	
	private String username;
	private String JWTToken;
	
	public JWTResponse(String username, String jWTToken) {
		this.username = username;
		this.JWTToken = jWTToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJWTToken() {
		return JWTToken;
	}

	public void setJWTToken(String jWTToken) {
		JWTToken = jWTToken;
	}
	
	
	
	

}
