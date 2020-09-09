package com.springAPI.model;


public class Result<T> {
	private int errorCode;
	private T data;
	public Result(int errorCode, T data) {
		this.errorCode = errorCode;
		this.data = data;
	}
	
	public Result(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	

}
