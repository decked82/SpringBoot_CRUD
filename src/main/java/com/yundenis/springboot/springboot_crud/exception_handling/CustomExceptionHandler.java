package com.yundenis.springboot.springboot_crud.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    private final DataInfoHandler dataInfoHandler;

    public CustomExceptionHandler(DataInfoHandler dataInfoHandler) {
        this.dataInfoHandler = dataInfoHandler;
    }

    @ExceptionHandler
    public ResponseEntity<DataInfoHandler> handleException(UserWithSuchLoginExist e) {
        return new ResponseEntity<>(dataInfoHandler.getInstanceWithInfo(e.getMessage()), HttpStatus.CONFLICT);
    }
}
