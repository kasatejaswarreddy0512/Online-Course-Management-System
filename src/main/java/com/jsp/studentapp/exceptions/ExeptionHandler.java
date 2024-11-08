package com.jsp.studentapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.studentapp.helper.ResponseStructure;

@RestControllerAdvice
public class ExeptionHandler {
	
	@ExceptionHandler(value=StudentNotFoundException.class)
	public ResponseStructure<StudentNotFoundException> exception1(StudentNotFoundException se){
		ResponseStructure<StudentNotFoundException> rs= new ResponseStructure<StudentNotFoundException>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData(se);
		rs.setMessage(se.getMessage());
		return rs;
	}
		
	
	

}
