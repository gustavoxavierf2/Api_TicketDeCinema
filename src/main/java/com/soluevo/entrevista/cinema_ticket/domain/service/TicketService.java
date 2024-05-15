package com.soluevo.entrevista.cinema_ticket.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluevo.entrevista.cinema_ticket.api.response.TicketResponse;
import com.soluevo.entrevista.cinema_ticket.api.request.TicketRequest;
import com.soluevo.entrevista.cinema_ticket.domain.handler.exception.IdException;
import com.soluevo.entrevista.cinema_ticket.domain.handler.exception.NoDataException;
import com.soluevo.entrevista.cinema_ticket.domain.model.Ticket;
import com.soluevo.entrevista.cinema_ticket.domain.repository.TicketRepository;
import com.soluevo.entrevista.cinema_ticket.web.Mapper.TicketMapper;

@Service
public class TicketService {
    @Autowired
    TicketRepository db;

    public TicketResponse getTicketDto(Long id){
        Optional<Ticket> ticket = db.findById(id);
        if (ticket.isPresent()) {
            return TicketMapper.toTicketResponse(ticket.get());
        }else{
            throw new IdException("O ticket não foi encontrado.", 404);
        } 
    }

    public List<TicketResponse> getTicketAllDto(){
        List<Ticket> tickets = db.findAll();
        List<TicketResponse> response = new ArrayList<>();
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                response.add(TicketMapper.toTicketResponse(ticket));
            }
            return response;
        }else{
            throw new NoDataException("Não ha nenhum ticket criado.", 404);
        }  
    }

    public TicketResponse creatDto(TicketRequest request){
        Ticket ticket = TicketMapper.toTicket(request);
        db.save(ticket);
        return TicketMapper.toTicketResponse(ticket);
    }

    public TicketResponse editDto(TicketRequest request){
        Optional<Ticket> ticketResponse = db.findById(request.getId());
        if (ticketResponse.isPresent()) {
                Ticket ticket = TicketMapper.toTicket(request);
                db.save(ticket);
                return TicketMapper.toTicketResponse(ticket);
        }else{
            throw new IdException("O ticket não foi encontrado.", 404);
        }  
    }

    public TicketResponse deleteDto(Long id){ 
        Optional<Ticket> ticket = db.findById(id);
        if (ticket.isPresent()) {
            db.delete(ticket.get());
            return TicketMapper.toTicketResponse(ticket.get());
        }else{
            throw new IdException("O ticket não foi encontrado.", 404);
        } 
    }
}
