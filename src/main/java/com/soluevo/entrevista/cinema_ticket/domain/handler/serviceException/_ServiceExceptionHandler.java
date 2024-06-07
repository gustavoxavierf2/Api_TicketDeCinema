package com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class _ServiceExceptionHandler {

    @ExceptionHandler(TicketVazioException.class)
    ProblemDetail handlerTicketVazioException(TicketVazioException ex){
        System.out.println("Log registrado... " + ex.getLocalizedMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setTitle("TicketVazioException");
        problemDetail.setProperty("documentation", "http://localhost:8080/swagger-ui/index.html");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stackTrace", ex.getStackTrace());

        return problemDetail;
    }

    @ExceptionHandler(DtoException.class)
    ProblemDetail handlerDtoException(DtoException ex){
        System.out.println("Log registrado... " + ex.getLocalizedMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setTitle("DtoException");
        problemDetail.setProperty("documentation", "http://localhost:8080/swagger-ui/index.html");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stackTrace", ex.getStackTrace());
        return problemDetail;
    }
}
