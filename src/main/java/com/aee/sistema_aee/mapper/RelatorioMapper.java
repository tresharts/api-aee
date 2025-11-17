package com.aee.sistema_aee.mapper;

import com.aee.sistema_aee.dto.request.RelatorioRequestDTO;
import com.aee.sistema_aee.dto.response.RelatorioResponseDTO;
import com.aee.sistema_aee.entity.Relatorio;

public class RelatorioMapper
{
    public static Relatorio toEntity(RelatorioRequestDTO dto)
    {
        Relatorio relatorio = new Relatorio();
        relatorio.setTipo(dto.getTipo());
        relatorio.setConteudo(dto.getConteudo());

        return relatorio;
    }

    public static RelatorioResponseDTO toDTO(Relatorio relatorio)
    {
        RelatorioResponseDTO dto = new RelatorioResponseDTO();
        dto.setId(relatorio.getId());
        dto.setDataRegistro(relatorio.getDataRegistro());
        dto.setTipo(relatorio.getTipo());
        dto.setConteudo(relatorio.getConteudo());

        if (relatorio.getAluno() != null)
        {
            dto.setAlunoId(relatorio.getAluno().getId());
        }

        if (relatorio.getProfessor() != null)
        {
            dto.setProfessorId(relatorio.getProfessor().getId());
        }

        return dto;
    }
}
