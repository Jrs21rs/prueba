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
@Entity(name = "Representante")
@Table(name = "Repre_bk")
public class representante implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REPRE")
    @SequenceGenerator(name = "SEQ_REPRE", sequenceName = "SEQ_REPRE", allocationSize = 1)
    @Column(name = "RPR_CODIGO", nullable = false)
    private long serial;

    @Column(name = "REPRE_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "REPRE_CONTACTO", nullable = false)
    private char contacto;

    @Column(name = "REPRE_USUARIO", unique = true, nullable = false)
    private String usuario;

    @Column(name = "REPRE_CONTRASEÑA", nullable = false)
    private String contrasena;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_REPRESENTANTE"));
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