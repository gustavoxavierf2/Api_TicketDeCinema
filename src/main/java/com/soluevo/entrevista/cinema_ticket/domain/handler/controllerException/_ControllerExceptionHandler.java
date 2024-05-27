package com.soluevo.entrevista.cinema_ticket.domain.handler.controllerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class _ControllerExceptionHandler {
    @ExceptionHandler(ResponseVazioException.class)
    public ResponseEntity<Object> HandlerResponseVazioException(ResponseVazioException ex){
        System.out.println("Log registrado... " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
