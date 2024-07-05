package com.soluevo.entrevista.cinema_ticket.domain.security.token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.soluevo.entrevista.cinema_ticket.domain.security.auth.model.Usuario;


@Service
public class TokenKeyService {
    @Value("${api.security.token.secret}")
    private String secret;

    private Instant ExpiracaoToken(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("api_ticketdecinema")
                .withSubject(usuario.getEmail())
                .withExpiresAt(ExpiracaoToken())
                .sign(algoritmo);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro na geração do token", e);
        }
       
    }

    public String validarToken(String token){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                .withIssuer("api_ticketdecinema")
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
