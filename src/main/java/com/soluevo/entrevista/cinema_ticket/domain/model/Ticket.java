package com.soluevo.entrevista.cinema_ticket.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id; 
    @Column(nullable = false) String personName;
    @Column(nullable = false) String cinemaName; 
    @Column(nullable = false) String movieName; 
    @Column(nullable = false) @JsonFormat(pattern = "HH:mm") LocalTime hour;
    @Column(nullable = false) @JsonFormat(pattern = "dd/MM/yyyy") LocalDate sessionDate; 
    @Column(nullable = false) Integer roomNumber;
    @Column(nullable = false) Float price; 
    @Column(nullable = false) String ticketType; 
    @Column(nullable = false) String seat;
}
