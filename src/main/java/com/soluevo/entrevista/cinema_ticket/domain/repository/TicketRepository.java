package com.soluevo.entrevista.cinema_ticket.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soluevo.entrevista.cinema_ticket.domain.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{}
