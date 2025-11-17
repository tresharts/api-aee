package com.aee.sistema_aee.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlunoResponseDTO
{
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private String turma;
    private Long professorId;
    private Long responsavelId;
}
