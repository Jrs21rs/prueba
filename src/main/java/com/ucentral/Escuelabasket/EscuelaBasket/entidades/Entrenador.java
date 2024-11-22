package com.ucentral.Escuelabasket.EscuelaBasket.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "Entrenador")
@Table(name = "Entren_bk")
public class Entrenador implements Serializable, UserDetails {

    @Id
    @Column(name = "ENTR_CEDULA", nullable = false)
    private long cedula;

    @Column(name = "ENTR_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "ENTR_CONTACTO", nullable = false)
    private double contacto;

    @Column(name = "ENTR_USUARIO", unique = true, nullable = false)
    private String usuario;

    @Column(name = "ENTR_CONTRASENA", nullable = false)
    private String contrasena;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ENTRENADOR"));
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
