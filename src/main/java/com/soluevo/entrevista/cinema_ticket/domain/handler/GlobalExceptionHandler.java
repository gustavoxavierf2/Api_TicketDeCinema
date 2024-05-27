package com.soluevo.entrevista.cinema_ticket.domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    String details = "{" +
                    "\n\t\"personName\": \"String\"," +
                    "\n\t\"cinemaName\": \"String\"," +
                    "\n\t\"movieName\": \"String\"," +
                    "\n\t\"hour\": \"00:00\"," +
                    "\n\t\"sessionDate\": \"01/01/2000\"," +
                    "\n\t\"roomNumber\": 0," +
                    "\n\t\"price\": 0.00," +
                    "\n\t\"ticketType\": \"String\"," +
                    "\n\t\"seat\": \"String\"" +
                    "\n}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + ex.getBody() + "\n\nExemplo de requisição: " + details);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao ler a mensagem HTTP: \n" + ex.getMessage() + "\n\nExemplo de requisição: " + details);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Violação de restrição do banco de dados JPA: " + ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado: " + ex.getMessage() + "\n\nCaminhos existentes: /soluevo/cinema/ticket\n ...");
    }
}
