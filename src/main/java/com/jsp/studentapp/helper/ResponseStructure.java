package com.jsp.studentapp.helper;

import java.util.List;

public class ResponseStructure<T> {
	
	private int statuscode;
	private T data;
	private String message;
	
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setData(List<T> datas) {
		for(T d:datas) {
			setData(d);	
		}
	}

}
