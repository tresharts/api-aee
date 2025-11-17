package com.aee.sistema_aee.mapper;

import com.aee.sistema_aee.dto.request.ResponsavelRequestDTO;
import com.aee.sistema_aee.dto.response.ResponsavelResponseDTO;
import com.aee.sistema_aee.entity.Responsavel;

public class ResponsavelMapper
{
    public static Responsavel toEntity(ResponsavelRequestDTO dto)
    {
        return new Responsavel(
                dto.getNome(),
                dto.getEmail(),
                dto.getCpf(),
                dto.getSenha(),
                dto.getTelefone()
        );
    }

    public static ResponsavelResponseDTO toDTO(Responsavel responsavel)
    {
        ResponsavelResponseDTO dto = new ResponsavelResponseDTO();

        dto.setId(responsavel.getId());
        dto.setNome(responsavel.getNome());
        dto.setEmail(responsavel.getEmail());
        dto.setTelefone(responsavel.getTelefone());

        return dto;
    }
}
