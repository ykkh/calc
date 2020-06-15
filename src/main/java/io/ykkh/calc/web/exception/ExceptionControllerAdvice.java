package io.ykkh.calc.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import io.ykkh.calc.web.ResponseInfo;



@ControllerAdvice
public class ExceptionControllerAdvice {
	
	


	@ExceptionHandler({ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class, ConstraintViolationException.class, ArithmeticException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseInfo handleBadRequest
	(HttpServletRequest req, Exception ex) {
		
		String errorMessage = ex.getMessage();
		return new ResponseInfo(HttpStatus.BAD_REQUEST, errorMessage, null);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseInfo handleBadRequestValidation
	(HttpServletRequest req, MethodArgumentNotValidException ex) {
		
		 Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		return new ResponseInfo(HttpStatus.BAD_REQUEST, errors.toString(), null);
	}
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseInfo handleServerError
	(HttpServletRequest req, Exception ex) {
		
		String errorMessage = ex.getMessage();
		return new ResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, null);
	}

	}