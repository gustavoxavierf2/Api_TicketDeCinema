package com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class _ServiceExceptionHandler {

    @ExceptionHandler(TicketVazioException.class)
    public ResponseEntity<Object> handlerTicketVazioException(TicketVazioException ex){
        System.out.println("Log registrado... " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DtoException.class)
    public ResponseEntity<Object> handlerDtoException(DtoException ex){
        System.out.println("Log registrado... " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
