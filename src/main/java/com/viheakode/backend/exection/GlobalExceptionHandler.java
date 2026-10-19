package com.viheakode.backend.exection;

import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        return ApiResponseStructure.error(ex.getMessage(), "Not Found", request.getRequestURI(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Object> handleDuplicateResource(DuplicateResourceException ex, HttpServletRequest request){
        return ApiResponseStructure.error(ex.getMessage(), "Conflict", request.getRequestURI(), HttpStatus.CONFLICT);
    }
}
