package com.aee.sistema_aee.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PDIResponseDTO
{
    private Long id;
    private Date dataInicial;
    private Date dataFim;
    private String objetivos;
    private Long alunoId;
}
