package com.udemy.spring.api.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.udemy.spring.api.cursomc.exceptions.ObjectNotFoundException;

@ControllerAdvice// Para declarar um handler
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)// Descreve qual a exception será tratada
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}

}
