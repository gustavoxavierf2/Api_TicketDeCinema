package com.soluevo.entrevista.cinema_ticket.domain.handler.exception;

import lombok.Getter;

@Getter
public class IdException extends RuntimeException{
    private Integer errorCode;

    public IdException() {}

    public IdException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
