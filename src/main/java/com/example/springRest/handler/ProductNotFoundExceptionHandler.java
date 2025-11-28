package com.example.springRest.handler;

import com.example.springRest.exception.productExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProductNotFoundExceptionHandler {
    @ExceptionHandler(productExistException.class)
    public ResponseEntity<Map<String, String>> handelProductNotFoundException(
            productExistException exp) {
        var errorResponse = new HashMap<String, String>();
        errorResponse.put("error", exp.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
