package com.soluevo.entrevista.cinema_ticket.domain.security.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soluevo.entrevista.cinema_ticket.domain.security.auth.controller.dto.request.LoginRequest;
import com.soluevo.entrevista.cinema_ticket.domain.security.auth.controller.dto.response.ResponseToken;
import com.soluevo.entrevista.cinema_ticket.domain.security.auth.model.Usuario;
import com.soluevo.entrevista.cinema_ticket.domain.security.token.TokenKeyService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/soluevo/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenKeyService tokenKeyService;
    
    @PostMapping("/login")
    public ResponseEntity<ResponseToken> autenticar(@RequestBody LoginRequest request){
        var emailSenha = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        
        var autenticado = this.authenticationManager.authenticate(emailSenha);

        var token = tokenKeyService.gerarToken((Usuario) autenticado.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseToken(token));
    }
}
