package com.example.demo.entity;

import org.springframework.http.HttpStatus;

public class ErrRes {
	private HttpStatus httpStatus;
	private String msg;
	
	public ErrRes(HttpStatus httpStatus, String msg) {
		this.httpStatus = httpStatus;
		this.msg = msg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
