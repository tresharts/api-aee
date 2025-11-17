package com.aee.sistema_aee.dto.response;

import com.aee.sistema_aee.enums.TipoRelatorio;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RelatorioResponseDTO
{
    private Long id;
    private Date dataRegistro;
    private TipoRelatorio tipo;
    private String conteudo;
    private Long alunoId;
    private Long professorId;
}
