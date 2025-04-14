package com.AllInOne.AllInOne.exceptions;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class MainExceptions{
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity CustomerNotFoundException(
			CustomerNotFoundException ex, WebRequest request) {
		CustomExceptionBody body = new CustomExceptionBody();
		body.setErrorCode(404);
		body.setErrorMessage(ex.getMessage());
		
		return new ResponseEntity(body, HttpStatus.NOT_FOUND);
	}
	
//	@Nullable
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		System.out.println("ABCD");
//		return null;
//	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        System.out.println("Hashmap is "+errors);
		CustomExceptionBody body = new CustomExceptionBody();
		body.setErrorCode(400);
		body.setErrorMessage(ex.getMessage());
		return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
	}
	
}
