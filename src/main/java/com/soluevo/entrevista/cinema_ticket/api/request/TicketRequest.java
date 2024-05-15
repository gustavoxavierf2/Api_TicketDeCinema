package com.soluevo.entrevista.cinema_ticket.api.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {
    Long id; 
    @NotBlank String personName;
    @NotBlank String cinemaName; 
    @NotBlank String movieName; 
    @Schema(example = "00:00") @NotNull @JsonFormat(pattern = "HH:mm") LocalTime hour;
    @Schema(description = "Data da sessão no formato dd/MM/yyyy", example = "01/01/2000") @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate sessionDate; 
    @NotNull Integer roomNumber;
    @NotNull Float price; 
    @NotBlank String ticketType; 
    @NotBlank String seat;   
}
