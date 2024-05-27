package com.soluevo.entrevista.cinema_ticket.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluevo.entrevista.cinema_ticket.api.response.TicketResponse;
import com.soluevo.entrevista.cinema_ticket.api.request.TicketRequest;
import com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException.TicketVazioException;
import com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException.DtoException;
import com.soluevo.entrevista.cinema_ticket.domain.model.Ticket;
import com.soluevo.entrevista.cinema_ticket.domain.repository.TicketRepository;
import com.soluevo.entrevista.cinema_ticket.web.Mapper.TicketMapper;

@Service
public class TicketService {
    @Autowired
    TicketRepository db;

    @Autowired
    TicketMapper ticketMapper;

    public TicketResponse getTicketDto(Long id){
        Optional<Ticket> ticket = db.findById(id);
       
        if (ticket.isEmpty()) {
            throw new TicketVazioException("O ticket não foi encontrado.");
        }else{
            return ticketMapper.toTicketResponse(ticket.get());       
        } 
    }

    public List<TicketResponse> getTicketAllDto(){
        List<Ticket> tickets = db.findAll();
        List<TicketResponse> response = new ArrayList<>();
        if (tickets.isEmpty()) {
            throw new TicketVazioException("Não ha nenhum ticket criado.");
        }else{
            for (Ticket ticket : tickets) {
                response.add(ticketMapper.toTicketResponse(ticket));
            }
            return response;    
        }  
    }

    public TicketResponse creatDto(TicketRequest request){
        Ticket ticket = ticketMapper.toTicket(request);
        if (ticket == null) {
            throw new DtoException("Error na transformação dos dados");
        }else{
            db.save(ticket);
            return ticketMapper.toTicketResponse(ticket);    
        }    
    }

    public TicketResponse editDto(TicketRequest request){
        Optional<Ticket> ticketResponse = db.findById(request.getId());
        if (ticketResponse.isEmpty()) {
            throw new TicketVazioException("O ticket não foi encontrado.");
        }else{
            Ticket ticket = ticketMapper.toTicket(request);
            db.save(ticket);
            return ticketMapper.toTicketResponse(ticket); 
        }  
    }

    public TicketResponse deleteDto(Long id){ 
        Optional<Ticket> ticket = db.findById(id);
        if (ticket.isEmpty()) {
            throw new TicketVazioException("O ticket não foi encontrado.");
        }else{
            db.delete(ticket.get());
            return ticketMapper.toTicketResponse(ticket.get());
        } 
    }
}
