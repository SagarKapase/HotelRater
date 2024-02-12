package com.lcwd.user.service.exception;

import com.lcwd.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler //this annotation is used to if any exceptions are there in the project then excute this method
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundEXception resourceNotFoundEXception){
        String message = resourceNotFoundEXception.getMessage();
        ApiResponse apiResponse = ApiResponse.builder()
                .message(message)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
