package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<String> HandlesallExceptions(Exception ex){
		return new ResponseEntity<String>("error"+ ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)	
	@ResponseBody
	public ResponseEntity<String> HandlesExceptions(ResourceNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidInputException.class)	
	@ResponseBody
	public ResponseEntity<String> Handlesinput(InvalidInputException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	

}
