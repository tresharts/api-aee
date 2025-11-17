package com.aee.sistema_aee.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsavelRequestDTO{
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private String telefone;
}