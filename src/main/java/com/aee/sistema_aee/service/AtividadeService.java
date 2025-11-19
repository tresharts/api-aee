package com.aee.sistema_aee.service;

import com.aee.sistema_aee.dto.request.AtividadeRequestDTO;
import com.aee.sistema_aee.dto.response.AtividadeResponseDTO;
import com.aee.sistema_aee.entity.Aluno;
import com.aee.sistema_aee.entity.Atividade;
import com.aee.sistema_aee.entity.Professor;
import com.aee.sistema_aee.exception.ResourceNotFoundException;
import com.aee.sistema_aee.mapper.AtividadeMapper;
import com.aee.sistema_aee.repository.AlunoRepository;
import com.aee.sistema_aee.repository.AtividadeRepository;
import com.aee.sistema_aee.repository.ProfessorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeService
{
    private final AtividadeRepository atividadeRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public AtividadeService(AtividadeRepository atividadeRepository,
                            AlunoRepository alunoRepository,
                            ProfessorRepository professorRepository)
    {
        this.atividadeRepository = atividadeRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public AtividadeResponseDTO criarAtividade(AtividadeRequestDTO dto)
    {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        Professor professor = professorRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        Atividade atividadeParaSalvar = AtividadeMapper.toEntity(dto);
        atividadeParaSalvar.setAluno(aluno);
        atividadeParaSalvar.setProfessor(professor);

        Atividade atividadeSalva = atividadeRepository.save(atividadeParaSalvar);

        return AtividadeMapper.toDTO(atividadeSalva);
    }

    public AtividadeResponseDTO buscarPorId(Long id)
    {
        Atividade atividadeEncontrada = atividadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada"));

        return AtividadeMapper.toDTO(atividadeEncontrada);
    }

    public Page<AtividadeResponseDTO> listarTodos(Pageable pageable)
    {
        Page<Atividade> paginaDeAtividade = atividadeRepository.findAll(pageable);

        return paginaDeAtividade.map(AtividadeMapper::toDTO);
    }

    public AtividadeResponseDTO atualizarAtividade(Long id, AtividadeRequestDTO dto)
    {
        Atividade atividadeExistente = atividadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada"));

        atividadeExistente.setTitulo(dto.getTitulo());
        atividadeExistente.setDescricao(dto.getDescricao());
        atividadeExistente.setDataEntrega(dto.getDataEntrega());

        Atividade atividadeAtualizada = atividadeRepository.save(atividadeExistente);

        return AtividadeMapper.toDTO(atividadeAtualizada);
    }

    public void deletarAtividade(Long id)
    {
        Atividade atividadeParaDeletar = atividadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada"));

        atividadeRepository.delete(atividadeParaDeletar);
    }
}
