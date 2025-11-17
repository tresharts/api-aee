package com.aee.sistema_aee.service;

import com.aee.sistema_aee.dto.request.RelatorioRequestDTO;
import com.aee.sistema_aee.dto.response.RelatorioResponseDTO;
import com.aee.sistema_aee.entity.Aluno;
import com.aee.sistema_aee.entity.Professor;
import com.aee.sistema_aee.entity.Relatorio;
import com.aee.sistema_aee.exception.ResourceNotFoundException;
import com.aee.sistema_aee.mapper.RelatorioMapper;
import com.aee.sistema_aee.repository.AlunoRepository;
import com.aee.sistema_aee.repository.ProfessorRepository;
import com.aee.sistema_aee.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService
{
    private final RelatorioRepository relatorioRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public RelatorioService(RelatorioRepository relatorioRepository,
                            AlunoRepository alunoRepository,
                            ProfessorRepository professorRepository)
    {
        this.relatorioRepository = relatorioRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public RelatorioResponseDTO criarRelatorio(RelatorioRequestDTO dto)
    {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        Professor professorAutor = professorRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        Relatorio relatorioParaSalvar = RelatorioMapper.toEntity(dto);
        relatorioParaSalvar.setAluno(aluno);
        relatorioParaSalvar.setProfessor(professorAutor);

        Relatorio relatorioSalvo = relatorioRepository.save(relatorioParaSalvar);
        return RelatorioMapper.toDTO(relatorioSalvo);
    }

    public RelatorioResponseDTO buscarPorId(Long id)
    {
        Relatorio relatorioEncontrado = relatorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relatório não encontrado"));

        return RelatorioMapper.toDTO(relatorioEncontrado);
    }

    public List<RelatorioResponseDTO> listarTodos()
    {
        return relatorioRepository
                .findAll()
                .stream()
                .map(RelatorioMapper::toDTO)
                .toList();
    }

    public void deletarRelatorio(Long id)
    {
        Relatorio relatorioParaDeletar = relatorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relatório não encontrado"));

        relatorioRepository.delete(relatorioParaDeletar);
    }
}
