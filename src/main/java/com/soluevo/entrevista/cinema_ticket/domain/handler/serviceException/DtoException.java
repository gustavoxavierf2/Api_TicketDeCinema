package com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException;

import lombok.Getter;

@Getter
public class DtoException extends RuntimeException {
    public DtoException(String message) {
        super(message);
    }

}
