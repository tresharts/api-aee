package com.aee.sistema_aee.mapper;

import com.aee.sistema_aee.dto.request.AtividadeRequestDTO;
import com.aee.sistema_aee.dto.response.AtividadeResponseDTO;
import com.aee.sistema_aee.entity.Atividade;
import com.aee.sistema_aee.enums.StatusAtividade;

public class AtividadeMapper
{
    public static Atividade toEntity(AtividadeRequestDTO dto)
    {
        Atividade atividade = new Atividade();
        atividade.setTitulo(dto.getTitulo());
        atividade.setDescricao(dto.getDescricao());
        atividade.setDataEntrega(dto.getDataEntrega());
        atividade.setStatus(StatusAtividade.PENDENTE);

        return atividade;
    }

    public static AtividadeResponseDTO toDTO(Atividade atividade)
    {
        AtividadeResponseDTO dto = new AtividadeResponseDTO();
        dto.setId(atividade.getId());
        dto.setTitulo(atividade.getTitulo());
        dto.setDescricao(atividade.getDescricao());
        dto.setDataEntrega(atividade.getDataEntrega());
        dto.setStatus(atividade.getStatus());
        dto.setObservacoes(atividade.getObservacoes());

        if (atividade.getAluno() != null)
        {
            dto.setAlunoId(atividade.getAluno().getId());
        }

        if (atividade.getProfessor() != null)
        {
            dto.setProfessorId(atividade.getProfessor().getId());
        }

        return dto;
    }
}
