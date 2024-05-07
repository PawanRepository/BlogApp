package com.pawan.blogapp.exceptions;


import java.util.HashMap;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.pawan.blogapp.payloads.Api_Response;

@RestControllerAdvice
public class GobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Api_Response> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
		String msg = e.getMessage();
		Api_Response api_response = new Api_Response(msg,false);
		
		return new ResponseEntity<Api_Response>(api_response,HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	
	public ResponseEntity<Map<String, String>> methodargnotvalid(MethodArgumentNotValidException e) {
		
		Map<String, String> msg = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->{
		String fieldname = ((FieldError)error).getField();
		String defaultMessage = error.getDefaultMessage();
		msg.put(fieldname, defaultMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(msg, HttpStatus.BAD_REQUEST);
	}

}
