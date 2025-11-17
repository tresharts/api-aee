package com.aee.sistema_aee.mapper;

import com.aee.sistema_aee.dto.request.ProfessorRequestDTO;
import com.aee.sistema_aee.dto.response.ProfessorResponseDTO;
import com.aee.sistema_aee.entity.Professor;

public class ProfessorMapper
{
    private ProfessorMapper() {}

    public static Professor toEntity(ProfessorRequestDTO dto)
    {
        return new Professor(dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getSenha());
    }

    public static ProfessorResponseDTO toDTO(Professor professor)
    {
        ProfessorResponseDTO dto = new ProfessorResponseDTO();
        dto.setId(professor.getId());
        dto.setNome(professor.getNome());
        dto.setEmail(professor.getEmail());
        return dto;
    }
}
