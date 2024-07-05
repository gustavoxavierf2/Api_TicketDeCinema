package com.soluevo.entrevista.cinema_ticket.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluevo.entrevista.cinema_ticket.domain.model.Ticket;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    // @Query(value = "",nativeQuery = true)
    // List<Ticket> findById(Long id);
}
