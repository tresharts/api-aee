package com.aee.sistema_aee.mapper;

import com.aee.sistema_aee.dto.request.PDIRequestDTO;
import com.aee.sistema_aee.dto.response.PDIResponseDTO;
import com.aee.sistema_aee.entity.PDI;

public class PDIMapper
{
    public static PDI toEntity(PDIRequestDTO dto)
    {
        PDI pdi = new PDI();
        pdi.setDataInicial(dto.getDataInicial());
        pdi.setDataFim(dto.getDataFim());
        pdi.setObjetivos(dto.getObjetivos());

        return pdi;
    }

    public static PDIResponseDTO toDTO(PDI pdi)
    {
        PDIResponseDTO dto = new PDIResponseDTO();
        dto.setId(dto.getId());
        dto.setDataInicial(dto.getDataInicial());
        dto.setDataFim(dto.getDataInicial());
        dto.setObjetivos(dto.getObjetivos());

        if (pdi.getAluno() != null)
        {
            dto.setAlunoId(pdi.getAluno().getId());
        }

        return dto;
    }
}
