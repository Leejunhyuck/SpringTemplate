package org.raccoon.com.jwt.global.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handler(AuthenticationException e, HttpServletRequest request){
        ErrorCode errorCode = ErrorCode.AUTHENTICATION_FAILED;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(),errorCode.getMessage());
        
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}