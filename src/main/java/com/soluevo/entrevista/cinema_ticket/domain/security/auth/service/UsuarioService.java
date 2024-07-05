package com.soluevo.entrevista.cinema_ticket.domain.security.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soluevo.entrevista.cinema_ticket.domain.security.auth.repository.UsuarioRepo;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepo.findByEmail(email);
    }
    
}
