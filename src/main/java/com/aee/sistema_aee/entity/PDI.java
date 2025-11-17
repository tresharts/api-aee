package com.aee.sistema_aee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pdi")
@Getter
@Setter
@NoArgsConstructor
public class PDI implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataInicial;

    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String objetivos;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    private Aluno aluno;

    public PDI(Date dataInicial, Date dataFim, String objetivos, Aluno aluno)
    {
        this.dataInicial = dataInicial;
        this.dataFim = dataFim;
        this.objetivos = objetivos;
        this.aluno = aluno;
    }
}
