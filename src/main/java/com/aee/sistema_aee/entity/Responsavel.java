package com.aee.sistema_aee.entity;

import com.aee.sistema_aee.enums.QualUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsavel")
@PrimaryKeyJoinColumn(name = "pessoa_id")
@Getter
@Setter
@NoArgsConstructor
public class Responsavel extends Pessoa
{
    private String telefone;

    @OneToMany(mappedBy = "responsavel")
    private List<Aluno> alunos = new ArrayList<>();

    public Responsavel(String nome, String email, String cpf, String senha, String telefone)
    {
        super(nome, email, cpf, senha, QualUsuario.RESPONSAVEL);
        this.telefone = telefone;
    }
}
