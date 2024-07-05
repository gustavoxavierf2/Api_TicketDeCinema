package com.soluevo.entrevista.cinema_ticket.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soluevo.entrevista.cinema_ticket.api.request.TicketRequest;
import com.soluevo.entrevista.cinema_ticket.api.response.TicketResponse;
import com.soluevo.entrevista.cinema_ticket.domain.handler.controllerException.ResponseVazioException;
import com.soluevo.entrevista.cinema_ticket.domain.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/soluevo/cinema/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Operation(summary = "Retorna um ticket por ID", description = "Retorna um ticket específico com base no ID.", tags = {"Tickets"})
    @Parameter(name = "id", description = "ID do ticket")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retorna o ticket encontrado"),
        @ApiResponse(responseCode = "404", description = "O ticket não foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable Long id){ 
        TicketResponse response = ticketService.getTicketDto(id);
        if (response == null) {
            throw new ResponseVazioException("response vazio");
        }
        return ResponseEntity.status(200).body(response); 
    }

    @Operation(summary = "Retorna uma lista de tickets", description = "Retorna uma lista de todos os tickets disponíveis.", tags = {"Tickets"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retorna uma lista de tickets encontrados"),
        @ApiResponse(responseCode = "404", description = "Lista de tickets não encontrada")
    })
    @GetMapping()
    public ResponseEntity<List<TicketResponse>> getTicketAll() {
        List<TicketResponse> response = ticketService.getTicketAllDto();
        if (response == null) {
            throw new ResponseVazioException("response vazio");
        }
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Cria um ticket", description = "Cria um novo ticket com base nos dados fornecidos.\n\n*Data da sessão no formato dd/MM/yyyy", tags = {"Tickets"})
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Ticket criado"),
    })
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody TicketRequest request){
        TicketResponse response = ticketService.creatDto(request);
        if (response == null) {
            throw new ResponseVazioException("response vazio");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Edita um ticket", description = "Edita um ticket existente com base no ID.", tags = {"Tickets"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket editado"),
        @ApiResponse(responseCode = "404", description = "O ticket não foi encontrado."),
    })
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> editTicket(@Valid @PathVariable Long id, @RequestBody TicketRequest request){
        request.setId(id);
        TicketResponse response = ticketService.editDto(request);
        if (response == null) {
            throw new ResponseVazioException("response vazio");
        }
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Deleta um ticket", description = "Deleta um ticket existente com base no ID.", tags = {"Tickets"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket deletado"),
        @ApiResponse(responseCode = "404", description = "O ticket não foi encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<TicketResponse> deleteTicket(@PathVariable Long id){
        TicketResponse response = ticketService.deleteDto(id);
        if (response == null) {
            throw new ResponseVazioException("response vazio");
        }
        return ResponseEntity.status(200).body(response);
    }
}