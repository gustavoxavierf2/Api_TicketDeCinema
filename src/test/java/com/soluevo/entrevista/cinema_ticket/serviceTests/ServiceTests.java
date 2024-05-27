package com.soluevo.entrevista.cinema_ticket.serviceTests;


import com.soluevo.entrevista.cinema_ticket.api.request.TicketRequest;
import com.soluevo.entrevista.cinema_ticket.api.response.TicketResponse;
import com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException.TicketVazioException;
import com.soluevo.entrevista.cinema_ticket.domain.handler.serviceException.DtoException;
import com.soluevo.entrevista.cinema_ticket.domain.model.Ticket;
import com.soluevo.entrevista.cinema_ticket.domain.repository.TicketRepository;
import com.soluevo.entrevista.cinema_ticket.domain.service.TicketService;
import com.soluevo.entrevista.cinema_ticket.web.Mapper.TicketMapper;

import java.util.List;

import  org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("CRUD")
public class ServiceTests {
    @Mock
    private TicketRepository db;

    @Mock
    private TicketMapper ticketMapper;

    @InjectMocks
    private TicketService ticketService;

    
    private static Ticket ticket;
    private static TicketRequest ticketRequest;
    private static TicketResponse ticketResponse;
   

    @BeforeAll
    static void setUp() {
        ticket = new Ticket(1L, "gustavo", "cinemark", "spider man", LocalTime.of(19, 00),
                LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");

        ticketRequest = new TicketRequest(1L, "gustavo", "cinemark", "spider man", LocalTime.of(19, 00),
                LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");

        ticketResponse = new TicketResponse("gustavo", "cinemark", "spider man", LocalTime.of(19, 00),
                LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");
    }

    @Nested
    class getTicketDto {

        @Test
        @RepeatedTest(2)
        @DisplayName("buscando ticket com sucesso 2x seguidas")
        void BuscarTicketComSucesso() {
            when(db.findById(1L)).thenReturn(Optional.of(ticket));
            when(ticketMapper.toTicketResponse(ticket)).thenReturn(ticketResponse);

            TicketResponse response = ticketService.getTicketDto(1L);

            assertNotNull(response);
            assertEquals(ticketResponse, response);
            
          
            verify(db, times(1)).findById(1L);
            verify(ticketMapper).toTicketResponse(ticket);
        }

        @Test
        void LançarIdExceptionTicketNaoEncontrado() {
            when(db.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(TicketVazioException.class, () -> ticketService.getTicketDto(anyLong()));
            

            
            verify(db).findById(anyLong());
            verifyNoInteractions(ticketMapper);
        }
    }

    @Nested
    class getTicketAllDto {

        @Test
        void BuscarListaDeTicketComSucesso() {
            when(db.findAll()).thenReturn(Collections.singletonList(ticket));
            when(ticketMapper.toTicketResponse(ticket)).thenReturn(ticketResponse);

            List<TicketResponse> response = ticketService.getTicketAllDto();

            assertNotNull(response);
            assertEquals(1, response.size());
            assertEquals(ticketResponse, response.get(0)); 

            
            verify(db).findAll();
            verify(ticketMapper).toTicketResponse(ticket);
        }

        @Test
        void LançarNoDataExceptionTicketNaoEncontrado() {
            when(db.findAll()).thenReturn(Collections.emptyList());

            assertThrows(TicketVazioException.class, () -> ticketService.getTicketAllDto());

            
            verify(db).findAll();
            verifyNoInteractions(ticketMapper);
        }
    }

    @Nested
    class creatDto {
        @Test
        void CriarTicketComSucesso() {
            when(ticketMapper.toTicket(ticketRequest)).thenReturn(ticket);
            when(ticketMapper.toTicketResponse(ticket)).thenReturn(ticketResponse);

            TicketResponse response = ticketService.creatDto(ticketRequest);

            assertNotNull(response);
            assertEquals(ticketResponse, response);

            
            verify(ticketMapper).toTicket(ticketRequest);
            verify(db).save(ticket);
            verify(ticketMapper).toTicketResponse(ticket);
        }

        @Test
        void LançarRuntimeExceptionErroNaTransformaçaoDosDados() {
            when(ticketMapper.toTicket(any())).thenThrow(NullPointerException.class); 
            

            assertThrows(NullPointerException.class, () -> ticketService.creatDto(any()));

            verify(ticketMapper).toTicket(any());
            verifyNoInteractions(db);
        }

        @Test
        void LançarRuntimeExceptionErroNaTransformaçaoDosDados2() {
            when(ticketMapper.toTicket(any())).thenReturn(null); 
            

            assertThrows(DtoException.class, () -> ticketService.creatDto(any()));

            verify(ticketMapper).toTicket(any());
            verifyNoInteractions(db);
        }
    }

    @Nested
    class editDto {
        private Ticket ticketEditado;
        private TicketRequest ticketRequest;

        @BeforeEach
        void setUp() {
            ticketEditado = new Ticket(1L, "peter parker", "cinemark", "spider man", LocalTime.of(19, 00),
                    LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");

            ticketRequest = new TicketRequest(1L, "peter parker", "cinemark", "spider man", LocalTime.of(19, 00),
                    LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");

            ticketResponse = new TicketResponse("peter parker", "cinemark", "spider man", LocalTime.of(19, 00),
                    LocalDate.of(2024, 05, 20), 1, 16.00F, "meia", "a6");
        }

        @Test
        void EditarTicketComSucesso() {
            when(db.findById(ticketRequest.getId())).thenReturn(Optional.of(ticket));
            when(ticketMapper.toTicket(ticketRequest)).thenReturn(ticketEditado);
            when(ticketMapper.toTicketResponse(ticketEditado)).thenReturn(ticketResponse);

            TicketResponse response = ticketService.editDto(ticketRequest);

            assertNotNull(response);
            assertEquals(ticketResponse, response);

            verify(db).findById(ticketRequest.getId());
            verify(ticketMapper).toTicket(ticketRequest);
            verify(db).save(ticketEditado);
            verify(ticketMapper).toTicketResponse(ticketEditado);
        }

        @Test
        void LançarIdExceptionTicketNaoEncontrado() {
            when(db.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(TicketVazioException.class, () -> ticketService.editDto(ticketRequest));

            
            verify(db).findById(anyLong());
            verifyNoInteractions(ticketMapper);
        }

    }

    @Nested
    class deleteDto {

        @Test
        void DeletarTicketComSucesso() {
            when(db.findById(1L)).thenReturn(Optional.of(ticket));
            when(ticketMapper.toTicketResponse(ticket)).thenReturn(ticketResponse);

            TicketResponse response = ticketService.deleteDto(1L);

            assertNotNull(response);
            assertEquals(ticketResponse, response);

            
            verify(db).findById(1L);
            verify(db).delete(ticket);
            verify(ticketMapper).toTicketResponse(ticket);
        }

        @Test
        void LançarIdExceptionTicketNaoEncontrado() {
            when(db.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(TicketVazioException.class, () -> ticketService.deleteDto(anyLong()));

            
            verify(db).findById(anyLong());
            verifyNoInteractions(ticketMapper);
        }

        @Test
        @Disabled
        void LançarIdExceptionTicketNaoEncontrado2() {
            when(db.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(TicketVazioException.class, () -> ticketService.deleteDto(anyLong()));

            
            verify(db).findById(anyLong());
            verifyNoInteractions(ticketMapper);
        }

    }
}