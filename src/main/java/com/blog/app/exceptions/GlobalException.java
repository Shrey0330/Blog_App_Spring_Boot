package com.blog.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.app.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	String messsage=ex.getMessage();
			System.out.println(messsage);
ApiResponse apiresposnse=new ApiResponse(messsage, false);
return new ResponseEntity<ApiResponse>(apiresposnse,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public ResponseEntity<Map<String,String>> handleMethodArgs(MethodArgumentNotValidException ex)
	{
		Map<String,String> hmap=new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((action)->{
		String fieldName=((FieldError)action).getField()	;
		String message=action.getDefaultMessage();
		hmap.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(hmap,HttpStatus.BAD_REQUEST);
		
	}
}
