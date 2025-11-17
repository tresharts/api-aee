package com.aee.sistema_aee.mapper;

import com.aee.sistema_aee.dto.request.AlunoRequestDTO;
import com.aee.sistema_aee.dto.response.AlunoResponseDTO;
import com.aee.sistema_aee.entity.Aluno;

public class AlunoMapper
{
    public static Aluno toEntity(AlunoRequestDTO dto)
    {
        return new Aluno(
                dto.getNome(),
                dto.getEmail(),
                dto.getCpf(),
                dto.getDataNascimento(),
                dto.getTurma()
        );
    }

    public static AlunoResponseDTO toDTO(Aluno aluno)
    {
        AlunoResponseDTO dto = new AlunoResponseDTO();

        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setCpf(aluno.getCpf());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setTurma(aluno.getTurma());

        if (aluno.getResponsavel() != null)
        {
            dto.setResponsavelId(aluno.getResponsavel().getId());
        }

        return dto;
    }
}
