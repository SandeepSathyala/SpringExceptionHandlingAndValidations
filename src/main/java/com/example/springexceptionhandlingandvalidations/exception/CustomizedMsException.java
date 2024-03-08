package com.example.springexceptionhandlingandvalidations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedMsException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request){
        ErrorDetails errorsDetails = new ErrorDetails(ex.getMessage(), 500L,null);
        return new ResponseEntity<>(errorsDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProcessingException.class)
    public  final ResponseEntity<ErrorDetails> handleProcessingException(ProcessingException ex, WebRequest request){
        ErrorDetails errorsDetails = new ErrorDetails(ex.getMessage(), ex.getCode(),null);
        return new ResponseEntity<>(errorsDetails,HttpStatus.BAD_REQUEST);
    }
}
