package com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException;

import lombok.Getter;

@Getter
public class TicketVazioException extends RuntimeException {
    public TicketVazioException(String message) {
        super(message);
    }

}
