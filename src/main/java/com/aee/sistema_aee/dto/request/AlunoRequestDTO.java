package com.aee.sistema_aee.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlunoRequestDTO{
    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private String turma;
    private Long professorId;
    private Long responsavelId;
}