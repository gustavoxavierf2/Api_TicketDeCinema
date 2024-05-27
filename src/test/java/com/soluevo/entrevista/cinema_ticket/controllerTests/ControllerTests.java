package com.soluevo.entrevista.cinema_ticket.controllerTests;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.soluevo.entrevista.cinema_ticket.api.request.TicketRequest;
import com.soluevo.entrevista.cinema_ticket.api.response.TicketResponse;
import com.soluevo.entrevista.cinema_ticket.domain.handler.controllerException.ResponseVazioException;
import com.soluevo.entrevista.cinema_ticket.domain.service.TicketService;
import com.soluevo.entrevista.cinema_ticket.web.controller.TicketController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ControllerTests {
    @Mock
    TicketService ticketService;

    @InjectMocks
    static TicketController ticketController;

    private  MockMvc mockMvc;
    private  ObjectMapper objectMapper;
    private  TicketRequest ticketRequest;
    private  TicketResponse ticketResponse;

    @BeforeEach 
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        ticketRequest = new TicketRequest(1L, "gustavo", "cinemark", "spider man", LocalTime.of(19, 00),
                LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");

        ticketResponse = new TicketResponse("gustavo", "cinemark", "spider man", LocalTime.of(19, 00),
                LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");
    }

    @Nested
    class getTicket {
        @Test
        void RetornaTicketComSucesso() throws Exception {
            when(ticketService.getTicketDto(anyLong())).thenReturn(ticketResponse);

            mockMvc.perform(get("/soluevo/cinema/ticket/" + anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.personName").value(ticketResponse.getPersonName()))
                .andExpect(jsonPath("$.cinemaName").value(ticketResponse.getCinemaName()))
                .andExpect(jsonPath("$.movieName").value(ticketResponse.getMovieName()))
                .andExpect(jsonPath("$.hour").value(ticketResponse.getHour().toString()))
                .andExpect(jsonPath("$.roomNumber").value(ticketResponse.getRoomNumber()))
                .andExpect(jsonPath("$.price").value(ticketResponse.getPrice()))
                .andExpect(jsonPath("$.ticketType").value(ticketResponse.getTicketType()))
                .andExpect(jsonPath("$.seat").value(ticketResponse.getSeat()));
                
                //.andExpect(jsonPath("$.sessionDate").value(ticketResponse.getSessionDate().toString()));
                
        }
        @Test
        void LançarResponseVazioException() throws Exception {
            when(ticketService.getTicketDto(anyLong())).thenReturn(null);

            assertThrows(ResponseVazioException.class, () -> ticketController.getTicket(anyLong()));   
        }


    }

    @Nested
    class getTicketAll{
        @Test
        void RetornaAllTicketComSucesso() throws Exception {
            when(ticketService.getTicketAllDto()).thenReturn(Collections.singletonList(ticketResponse));

            mockMvc.perform(get("/soluevo/cinema/ticket")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
            
        }

        @Test
        void LançarResponseVazioException() throws Exception {
            when(ticketService.getTicketAllDto()).thenReturn(null);

            assertThrows(ResponseVazioException.class, () -> ticketController.getTicketAll());   
        }

    }

    @Nested
    class createTicket{
        @Test
        void RetornaCreatTicketComSucesso() throws Exception {
           
            when(ticketService.creatDto(any(TicketRequest.class))).thenReturn(ticketResponse);

            mockMvc.perform(post("/soluevo/cinema/ticket")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ticketRequest)))
            .andExpect(status().isCreated());
        }

        @Test
        void LançarResponseVazioException() throws Exception {
            when(ticketService.creatDto(ticketRequest)).thenReturn(null);

            assertThrows(ResponseVazioException.class, () -> ticketController.createTicket(ticketRequest));   
        }
    }

    @Nested
    class UpdateTicket {

        @Test
        void retornaUpdateTicketComSucesso() throws Exception {
            when(ticketService.editDto(any(TicketRequest.class) )).thenReturn(ticketResponse);

            mockMvc.perform(put("/soluevo/cinema/ticket/" + anyLong())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ticketRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.personName").value(ticketResponse.getPersonName()))
                    .andExpect(jsonPath("$.cinemaName").value(ticketResponse.getCinemaName()))
                    .andExpect(jsonPath("$.movieName").value(ticketResponse.getMovieName()))
                    .andExpect(jsonPath("$.hour").value(ticketResponse.getHour().toString()))
                    .andExpect(jsonPath("$.roomNumber").value(ticketResponse.getRoomNumber()))
                    .andExpect(jsonPath("$.price").value(ticketResponse.getPrice()))
                    .andExpect(jsonPath("$.ticketType").value(ticketResponse.getTicketType()))
                    .andExpect(jsonPath("$.seat").value(ticketResponse.getSeat()));
        }

        @Test
        void lançarResponseVazioException() throws Exception {
            when(ticketService.editDto(any(TicketRequest.class))).thenReturn(null);

            assertThrows(ResponseVazioException.class, () -> ticketController.editTicket(anyLong(),ticketRequest));   
            
        }
    }

    @Nested
    class DeleteTicket {

        @Test
        void retornaDeleteTicketComSucesso() throws Exception {
            when(ticketService.deleteDto(anyLong())).thenReturn(ticketResponse);

            mockMvc.perform(delete("/soluevo/cinema/ticket/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        void lançarResponseVazioException() throws Exception {
            when(ticketService.deleteDto(anyLong())).thenReturn(null);

            assertThrows(ResponseVazioException.class, () -> ticketController.deleteTicket(anyLong()));   
            
        }
    }
}

