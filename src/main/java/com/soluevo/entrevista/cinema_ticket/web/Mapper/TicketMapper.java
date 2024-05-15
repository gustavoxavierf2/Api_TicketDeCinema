package com.soluevo.entrevista.cinema_ticket.web.Mapper;

import com.soluevo.entrevista.cinema_ticket.api.request.TicketRequest;
import com.soluevo.entrevista.cinema_ticket.api.response.TicketResponse;
import com.soluevo.entrevista.cinema_ticket.domain.model.Ticket;

public class TicketMapper {
    public static Ticket toTicket(TicketRequest request){
        Ticket ticket = new Ticket(request.getId(), request.getPersonName(), 
        request.getCinemaName(), request.getMovieName(), request.getHour(), request.getSessionDate(), 
        request.getRoomNumber(), request.getPrice(), request.getTicketType(), request.getSeat());

        return ticket;
    }

    public static TicketResponse toTicketResponse(Ticket request){
        TicketResponse response = new TicketResponse(request.getPersonName(), 
        request.getCinemaName(), request.getMovieName(), request.getHour(), request.getSessionDate(), 
        request.getRoomNumber(), request.getPrice(), request.getTicketType(), request.getSeat());

        return response;
    }
}
