package com.aee.sistema_aee.entity;

import com.aee.sistema_aee.enums.QualUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "pessoa_id")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Professor extends Pessoa
{
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Aluno> alunos = new ArrayList<>();

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Atividade> atividades = new ArrayList<>();

    public Professor(String nome, String email, String cpf, String senha)
    {
        super(nome, email, cpf, senha, QualUsuario.PROFESSOR);
    }
}
