package com.aee.sistema_aee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "pessoa_id")
@Getter
@Setter
@NoArgsConstructor
public class Aluno extends Pessoa
{
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "turma")
    private String turma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private PDI pdi;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relatorio> relatorios = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades = new ArrayList<>();

    public Aluno(String nome, String email, String cpf, Date dataNascimento, String turma) {
        super(nome, email, cpf, null, null);
        this.dataNascimento = dataNascimento;
        this.turma = turma;
    }

    public void setPDI(PDI pdi)
    {
        if (pdi != null)
        {
            pdi.setAluno(this);
        }
        this.pdi = pdi;
    }
}
