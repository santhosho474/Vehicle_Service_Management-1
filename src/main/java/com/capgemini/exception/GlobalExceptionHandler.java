package com.capgemini.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value =  Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception e) {
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMesssage(e.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(value =  UserDetailsException.class)
	public ResponseEntity<ErrorDetails> handleUserDetailsException(UserDetailsException e) {
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMesssage(e.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(value =  MechanicsException.class)
	public ResponseEntity<ErrorDetails> handleMechanicsException(MechanicsException e) {
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMesssage(e.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
}
}
