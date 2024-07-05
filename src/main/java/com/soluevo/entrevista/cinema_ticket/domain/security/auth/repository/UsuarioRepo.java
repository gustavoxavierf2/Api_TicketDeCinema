package com.soluevo.entrevista.cinema_ticket.domain.security.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluevo.entrevista.cinema_ticket.domain.security.auth.model.Usuario;


@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String email);
}
