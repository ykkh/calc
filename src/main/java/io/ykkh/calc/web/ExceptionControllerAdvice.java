package io.ykkh.calc.web;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;



@ControllerAdvice
public class ExceptionControllerAdvice {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseInfo handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return new ResponseInfo(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
	}
	
	
	
	@ExceptionHandler({ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class, ConstraintViolationException.class, ArithmeticException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseInfo handleBadRequest
	(HttpServletRequest req, Exception ex) {
		
		StringWriter errors = new StringWriter();
		logger.error(errors.toString());
		
		String errorMessage = ex.getMessage();
		return new ResponseInfo(HttpStatus.BAD_REQUEST, errorMessage, null);
	}


	}