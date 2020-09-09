package com.springAPI.model;

public class ResultError {
	private int errorCode;
	private String message;
	public ResultError(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
}
