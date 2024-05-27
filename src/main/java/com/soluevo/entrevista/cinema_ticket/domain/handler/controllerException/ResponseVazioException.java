package com.soluevo.entrevista.cinema_ticket.domain.handler.controllerException;

public class ResponseVazioException extends RuntimeException{
    public ResponseVazioException(String message){
        super(message);
    }
}
