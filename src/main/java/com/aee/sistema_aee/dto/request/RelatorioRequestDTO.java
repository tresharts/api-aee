package com.aee.sistema_aee.dto.request;

import com.aee.sistema_aee.enums.TipoRelatorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioRequestDTO {
    private TipoRelatorio tipo;
    private String conteudo;
    private Long alunoId;
}