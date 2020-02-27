package com.bridgelabz.FundooApp.Exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.FundooApp.ErrorException;
@ControllerAdvice
public class CustomGlobalexceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	//@ExceptionHandler(UserExceptions.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		 List<String> details = new ArrayList<>();
	        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	            details.add(error.getDefaultMessage());
	        }
		ErrorException error=new ErrorException("server Error", details);
		
		
          Response exception=new Response(new Date(), "information should not empty", 400,null);
          return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(UserExceptions.class)
public final ResponseEntity<Object>handleUserNameNotFoundException(UserExceptions ex,WebRequest request)
{
	Response exception=new Response(new Date(),ex.getMessage(), 404,null);
    return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);

}
	@ExceptionHandler(NoteException.class)
	public final ResponseEntity<Object>handleUserNameNotFoundException(NoteException ex,WebRequest request)
	{
		Response exception=new Response(new Date(),ex.getMessage(), 404,null);
	    return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(LabelException.class)
	public final ResponseEntity<Object>handleLabelNotFoundException(LabelException ex,WebRequest request)
	{
		Response exception=new Response(new Date(),ex.getMessage(), 404,null);
	    return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);

	}
}
