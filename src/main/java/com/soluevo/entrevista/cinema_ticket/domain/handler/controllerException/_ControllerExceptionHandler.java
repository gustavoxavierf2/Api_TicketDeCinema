package com.soluevo.entrevista.cinema_ticket.domain.handler.controllerException;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class _ControllerExceptionHandler {
    @ExceptionHandler(ResponseVazioException.class)
    ProblemDetail HandlerResponseVazioException(ResponseVazioException ex){
        System.out.println("Log registrado... " + ex.getMessage());
        
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setTitle("ResponseVazioException");
        problemDetail.setProperty("documentation", "http://localhost:8080/swagger-ui/index.html");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stackTrace", ex.getStackTrace());

        return problemDetail;
    }
}
