package com.aee.sistema_aee.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AtividadeRequestDTO{
    private String titulo;
    private String descricao;
    private Date dataEntrega;
    private Long alunoId;
}