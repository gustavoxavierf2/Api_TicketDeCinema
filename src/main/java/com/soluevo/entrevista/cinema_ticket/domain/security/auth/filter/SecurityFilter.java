package com.soluevo.entrevista.cinema_ticket.domain.security.auth.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.soluevo.entrevista.cinema_ticket.domain.security.auth.repository.UsuarioRepo;
import com.soluevo.entrevista.cinema_ticket.domain.security.token.TokenKeyService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired
    TokenKeyService tokenKeyService;

    @Autowired
    UsuarioRepo usuarioRepo;

    private String recuperarToken(HttpServletRequest request){
        var cabecalho = request.getHeader("Authorization");
        if (cabecalho == null) {
            return null;
        }else{
            return cabecalho.replace("Bearer ", "");  
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = recuperarToken(request);
                if (token != null) {
                    var login = tokenKeyService.validarToken(token);
                    
                    UserDetails usuario = usuarioRepo.findByEmail(login);

                    var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
                filterChain.doFilter(request, response);
    }
}
