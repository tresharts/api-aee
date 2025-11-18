package com.aee.sistema_aee.service;

import com.aee.sistema_aee.dto.request.ProfessorRequestDTO;
import com.aee.sistema_aee.dto.response.ProfessorResponseDTO;
import com.aee.sistema_aee.entity.Pessoa;
import com.aee.sistema_aee.entity.Professor;
import com.aee.sistema_aee.exception.DataIntegrityException;
import com.aee.sistema_aee.exception.ResourceNotFoundException;
import com.aee.sistema_aee.mapper.ProfessorMapper;
import com.aee.sistema_aee.repository.PessoaRepository;
import com.aee.sistema_aee.repository.ProfessorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService
{
    private final PessoaRepository pessoaRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(PessoaRepository pessoaRepository,
                            ProfessorRepository professorRepository,
                            PasswordEncoder passwordEncoder)
    {
        this.pessoaRepository = pessoaRepository;
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto)
    {
        if (pessoaRepository.findByEmail(dto.getEmail()).isPresent())
        {
            throw new DataIntegrityException("Email já cadastrado");
        }
        if (pessoaRepository.findByCpf(dto.getCpf()).isPresent())
        {
            throw new DataIntegrityException("Cpf já cadastrado");
        }

        Professor professorSalvar = ProfessorMapper.toEntity(dto);
        String senhaCript = passwordEncoder.encode(dto.getSenha());
        professorSalvar.setSenha(senhaCript);
        Professor professorSalvo = professorRepository.save(professorSalvar);
        return ProfessorMapper.toDTO(professorSalvo);
    }

    public ProfessorResponseDTO buscarProfessorPorId(Long id)
    {
        Professor professorEncontrado = professorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        return ProfessorMapper.toDTO(professorEncontrado);
    }

    public List<ProfessorResponseDTO> listarTodos()
    {
        return professorRepository
                .findAll()
                .stream()
                .map(ProfessorMapper::toDTO)
                .toList();
    }

    public ProfessorResponseDTO atualizarProfessor(Long id, ProfessorRequestDTO dto)
    {
        Professor professorAntigo = professorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        Optional<Pessoa> pessoaComEmail = pessoaRepository.findByEmail(dto.getEmail());

        if (pessoaComEmail.isPresent() && !pessoaComEmail.get().getId().equals(id))
        {
            throw new DataIntegrityException("Email já pertence a outro usuário");
        }

        Optional<Pessoa> pessoaComCpf = pessoaRepository.findByCpf(dto.getCpf());

        if (pessoaComCpf.isPresent() && !pessoaComCpf.get().getId().equals(id))
        {
            throw new DataIntegrityException("Cpf já pertence a outro usuário");
        }

        professorAntigo.setNome(dto.getNome());
        professorAntigo.setEmail(dto.getEmail());
        professorAntigo.setCpf(dto.getCpf());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty())
        {
            professorAntigo.setSenha(dto.getSenha());
        }

        Professor professorAtualizado = professorRepository.save(professorAntigo);

        return ProfessorMapper.toDTO(professorAtualizado);
    }

    public void deletarProfessor(Long id)
    {
        Professor professorRemovido = professorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        professorRepository.delete(professorRemovido);
    }
}
