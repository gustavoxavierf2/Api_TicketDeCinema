package com.soluevo.entrevista.cinema_ticket.domain.security.auth.model;

import java.util.List;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_LOGIN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) 
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private UsuarioEnumRole role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioEnumRole.ADMIN) {
           return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
           new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }
    @Override
    public String getPassword() {
        return getSenha();
    }
    @Override
    public String getUsername() {
        return getEmail();
    }


}
