package com.aee.sistema_aee.dto.response;

import com.aee.sistema_aee.enums.StatusAtividade;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AtividadeResponseDTO
{
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataEntrega;
    private StatusAtividade status;
    private String observacoes;
    private Long alunoId;
    private Long professorId;
}
