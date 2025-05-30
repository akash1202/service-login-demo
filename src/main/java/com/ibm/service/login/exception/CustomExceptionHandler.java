package com.ibm.service.login.exception;

import com.ibm.service.login.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest webRequest){
            List<String> details= new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ErrorResponse error=new ErrorResponse("Server Error",details);
            return  new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> details=new ArrayList<>();
        for(ObjectError error: ex.getAllErrors()){
            details.add(error.getDefaultMessage());
        }
        Map<String,Object> body=new HashMap<>();

        ErrorResponse errorResponse= new ErrorResponse("Validation failed",details);
        body.put("error",errorResponse);
        return ResponseEntity.badRequest().body(body);
    }
}
