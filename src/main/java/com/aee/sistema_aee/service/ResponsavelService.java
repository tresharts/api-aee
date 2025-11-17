package com.aee.sistema_aee.service;

import com.aee.sistema_aee.dto.request.ResponsavelRequestDTO;
import com.aee.sistema_aee.dto.response.ResponsavelResponseDTO;
import com.aee.sistema_aee.entity.Pessoa;
import com.aee.sistema_aee.entity.Responsavel;
import com.aee.sistema_aee.exception.DataIntegrityException;
import com.aee.sistema_aee.exception.ResourceNotFoundException;
import com.aee.sistema_aee.mapper.ResponsavelMapper;
import com.aee.sistema_aee.repository.PessoaRepository;
import com.aee.sistema_aee.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService
{
    private final ResponsavelRepository responsavelRepository;
    private final PessoaRepository pessoaRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository, PessoaRepository pessoaRepository)
    {
        this.responsavelRepository = responsavelRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public ResponsavelResponseDTO criarResponsavel(ResponsavelRequestDTO dto)
    {
        if (pessoaRepository.findByEmail(dto.getEmail()).isPresent())
        {
            throw new DataIntegrityException("Email já cadastrado");
        }

        if (pessoaRepository.findByCpf(dto.getCpf()).isPresent())
        {
            throw new DataIntegrityException("CPF já cadastrado");
        }

        Responsavel responsavelParaSalvar = ResponsavelMapper.toEntity(dto);
        Responsavel responsavelSalvo = responsavelRepository.save(responsavelParaSalvar);

        return ResponsavelMapper.toDTO(responsavelSalvo);
    }

    public ResponsavelResponseDTO buscarPorId(Long id)
    {
        Responsavel responsavelEncontrado = responsavelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado"));

        return ResponsavelMapper.toDTO(responsavelEncontrado);
    }

    public List<ResponsavelResponseDTO> listarTodos()
    {
        return responsavelRepository
                .findAll()
                .stream()
                .map(ResponsavelMapper::toDTO)
                .toList();
    }

    public ResponsavelResponseDTO atualizarResponsavel(Long id, ResponsavelRequestDTO dto)
    {
        Responsavel responsavelExistente = responsavelRepository.findById(id)
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

        responsavelExistente.setNome(dto.getNome());
        responsavelExistente.setEmail(dto.getEmail());
        responsavelExistente.setCpf(dto.getCpf());
        responsavelExistente.setTelefone(dto.getTelefone());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty())
        {
            responsavelExistente.setSenha(dto.getSenha());
        }

        Responsavel responsavelAtualizado = responsavelRepository.save(responsavelExistente);

        return ResponsavelMapper.toDTO(responsavelAtualizado);
    }

    public void deletarResponsavel(Long id)
    {
        Responsavel responsavelParaDeletar = responsavelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado"));

        responsavelRepository.delete(responsavelParaDeletar);
    }
}
