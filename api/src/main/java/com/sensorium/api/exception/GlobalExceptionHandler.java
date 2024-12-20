package com.sensorium.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionMessage resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		return ExceptionMessage.builder().status(HttpStatus.NOT_FOUND.value()).error(HttpStatus.NOT_FOUND.toString())
				.message(ex.getMessage()).build();
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(BindException ex) {

		ValidationErrorResponse errors = new ValidationErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.BAD_REQUEST.value());
		errors.setMessage("Validation Failed");

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.addError(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionMessage runtimeExceptionHandler(RuntimeException ex) {
		return ExceptionMessage.builder().status(HttpStatus.BAD_REQUEST.value()).error(HttpStatus.BAD_REQUEST.name())
				.message(ex.getMessage()).build();
	}

}
