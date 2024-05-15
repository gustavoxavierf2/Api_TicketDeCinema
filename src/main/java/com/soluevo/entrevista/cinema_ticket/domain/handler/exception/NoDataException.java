package com.soluevo.entrevista.cinema_ticket.domain.handler.exception;

import lombok.Getter;

@Getter
public class NoDataException extends RuntimeException {
    private Integer errorCode;

    public NoDataException() {}

    public NoDataException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
