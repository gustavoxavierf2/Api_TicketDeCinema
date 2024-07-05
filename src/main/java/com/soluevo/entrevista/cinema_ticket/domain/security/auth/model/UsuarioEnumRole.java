package com.soluevo.entrevista.cinema_ticket.domain.security.auth.model;

import lombok.Getter;

@Getter
public enum UsuarioEnumRole {
    ADMIN("admin"),
    COMUM("comum");

    private String role;

    UsuarioEnumRole(String role){
        this.role = role;
    }
}
