package com.aee.sistema_aee.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public  class PDIRequestDTO{
    private Date dataInicial;
    private Date dataFim;
    private String objetivos;
    private Long alunoId;
}