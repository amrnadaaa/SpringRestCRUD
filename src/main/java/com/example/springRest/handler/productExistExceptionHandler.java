package com.example.springRest.handler;

import com.example.springRest.exception.productExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class productExistExceptionHandler {
    @ExceptionHandler(productExistException.class)
    public ResponseEntity<Map<String, String>> handelProductAlreadyExistsExceptionHandler(
            productExistException exp) {
        var errorResponse = new HashMap<String, String>();
        errorResponse.put("error", exp.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
