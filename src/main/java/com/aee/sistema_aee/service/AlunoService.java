package com.aee.sistema_aee.service;

import com.aee.sistema_aee.dto.request.AlunoRequestDTO;
import com.aee.sistema_aee.dto.response.AlunoResponseDTO;
import com.aee.sistema_aee.entity.Aluno;
import com.aee.sistema_aee.entity.Pessoa;
import com.aee.sistema_aee.entity.Professor;
import com.aee.sistema_aee.entity.Responsavel;
import com.aee.sistema_aee.exception.DataIntegrityException;
import com.aee.sistema_aee.exception.ResourceNotFoundException;
import com.aee.sistema_aee.mapper.AlunoMapper;
import com.aee.sistema_aee.repository.AlunoRepository;
import com.aee.sistema_aee.repository.PessoaRepository;
import com.aee.sistema_aee.repository.ProfessorRepository;
import com.aee.sistema_aee.repository.ResponsavelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService
{
    private final AlunoRepository alunoRepository;
    private final PessoaRepository pessoaRepository;
    private final ProfessorRepository professorRepository;
    private final ResponsavelRepository responsavelRepository;

    public AlunoService(AlunoRepository alunoRepository,
                        PessoaRepository pessoaRepository,
                        ProfessorRepository professorRepository,
                        ResponsavelRepository responsavelRepository)
    {
        this.alunoRepository = alunoRepository;
        this.pessoaRepository = pessoaRepository;
        this.professorRepository = professorRepository;
        this.responsavelRepository = responsavelRepository;
    }

    public AlunoResponseDTO criarAluno(AlunoRequestDTO dto)
    {
        if (pessoaRepository.findByEmail(dto.getEmail()).isPresent())
        {
            throw new DataIntegrityException("Email já cadastrado");
        }

        if (pessoaRepository.findByCpf(dto.getCpf()).isPresent())
        {
            throw new DataIntegrityException("CPF já cadastrado");
        }

        Professor professorVinculado = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        Responsavel responsavelVinculado = responsavelRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado"));

        Aluno alunoParaSalvar = AlunoMapper.toEntity(dto);

        alunoParaSalvar.setProfessor(professorVinculado);
        alunoParaSalvar.setResponsavel(responsavelVinculado);

        Aluno alunoSalvo = alunoRepository.save(alunoParaSalvar);

        return AlunoMapper.toDTO(alunoParaSalvar);
    }

    public AlunoResponseDTO buscarPorId(Long id)
    {
        Aluno alunoEncontrado = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        return AlunoMapper.toDTO(alunoEncontrado);
    }

    public Page<AlunoResponseDTO> listarTodos(Pageable pageable)
    {
        Page<Aluno> paginaDeAlunos = alunoRepository.findAll(pageable);
        return paginaDeAlunos.map(AlunoMapper::toDTO);
    }

    public AlunoResponseDTO atualizarAluno(Long id, AlunoRequestDTO dto)
    {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        Professor professorAtualizado = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        Responsavel responsavelAtualizado = responsavelRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado"));

        Optional<Pessoa> pessoaComEmail = pessoaRepository.findByEmail(dto.getEmail());
        if (pessoaComEmail.isPresent() && !pessoaComEmail.get().getId().equals(id))
        {
            throw new DataIntegrityException("Email já pertence a outro usuário");
        }

        Optional<Pessoa> pessoaComCpf = pessoaRepository.findByCpf(dto.getCpf());
        if (pessoaComCpf.isPresent() && !pessoaComCpf.get().getId().equals(id))
        {
            throw new DataIntegrityException("CPF já pertence a outro usuário");
        }

        alunoExistente.setNome(dto.getNome());
        alunoExistente.setEmail(dto.getEmail());
        alunoExistente.setCpf(dto.getCpf());
        alunoExistente.setDataNascimento(dto.getDataNascimento());
        alunoExistente.setTurma(dto.getTurma());
        alunoExistente.setProfessor(professorAtualizado);
        alunoExistente.setResponsavel(responsavelAtualizado);

        Aluno alunoAtualizado = alunoRepository.save(alunoExistente);

        return AlunoMapper.toDTO(alunoAtualizado);
    }

    public void deletarAluno(Long id)
    {
        Aluno alunoParaDeletar = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        alunoRepository.delete(alunoParaDeletar);
    }
}
