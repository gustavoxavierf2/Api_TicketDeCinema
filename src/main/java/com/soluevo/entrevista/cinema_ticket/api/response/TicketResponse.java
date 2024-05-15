package com.soluevo.entrevista.cinema_ticket.api.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketResponse {
    String personName;
    String cinemaName; 
    String movieName; 
    @JsonFormat(pattern = "HH:mm") LocalTime hour;
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate sessionDate; 
    Integer roomNumber;
    Float price; 
    String ticketType; 
    String seat;   
}
