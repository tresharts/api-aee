package com.aee.sistema_aee.entity;

import com.aee.sistema_aee.enums.QualUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public abstract class Pessoa implements Serializable, UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = true)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private QualUsuario qualUsuario;

    public Pessoa(String nome, String email, String cpf, String senha, QualUsuario qualUsuario)
    {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.qualUsuario = qualUsuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        if (this.qualUsuario != null)
        {
            return List.of(new SimpleGrantedAuthority("ROLE_" + this.qualUsuario.name()));
        }

        return List.of();
    }

    @Override
    public String getPassword()
    {
        return this.senha;
    }

    public String getUsername()
    {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}
