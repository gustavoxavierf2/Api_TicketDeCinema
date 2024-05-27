package com.soluevo.entrevista.cinema_ticket.api.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketRequest {
    
    Long id; 
    @NotBlank String personName;
    @NotBlank String cinemaName; 
    @NotBlank String movieName; 
    @Schema(example = "00:00") @NotNull @JsonFormat(pattern = "HH:mm") LocalTime hour;
    @NotNull LocalDate sessionDate; 
    @NotNull Integer roomNumber;
    @NotNull Float price; 
    @NotBlank String ticketType; 
    @NotBlank String seat;   
}
