package com.aee.sistema_aee.entity;

import com.aee.sistema_aee.enums.QualUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class Pessoa implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
