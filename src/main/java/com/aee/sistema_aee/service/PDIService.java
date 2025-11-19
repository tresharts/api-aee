package com.aee.sistema_aee.service;

import com.aee.sistema_aee.dto.request.PDIRequestDTO;
import com.aee.sistema_aee.dto.response.PDIResponseDTO;
import com.aee.sistema_aee.entity.Aluno;
import com.aee.sistema_aee.entity.PDI;
import com.aee.sistema_aee.exception.DataIntegrityException;
import com.aee.sistema_aee.exception.ResourceNotFoundException;
import com.aee.sistema_aee.mapper.PDIMapper;
import com.aee.sistema_aee.repository.AlunoRepository;
import com.aee.sistema_aee.repository.PDIRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PDIService
{
    private final PDIRepository pdiRepository;
    private final AlunoRepository alunoRepository;

    public PDIService(PDIRepository pdiRepository, AlunoRepository alunoRepository)
    {
        this.pdiRepository = pdiRepository;
        this.alunoRepository = alunoRepository;
    }

    public PDIResponseDTO criarPDI(PDIRequestDTO dto)
    {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        Optional<PDI> pdiExistente = pdiRepository.findByAlunoId(dto.getAlunoId());

        if (pdiExistente.isPresent())
        {
            throw new DataIntegrityException("Este aluno já possui um Plano de Desenvolvimento Individual");
        }

        PDI pdiParaSalvar = PDIMapper.toEntity(dto);
        pdiParaSalvar.setAluno(aluno);

        PDI pdiSalvo = pdiRepository.save(pdiParaSalvar);

        return PDIMapper.toDTO(pdiSalvo);
    }

    public PDIResponseDTO buscarPorId(Long id)
    {
        PDI pdiEncontrado = pdiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano de Desenvolvimento Individual não encontrado"));

        return PDIMapper.toDTO(pdiEncontrado);
    }

    public PDIResponseDTO buscarPorAlunoId(Long alunoId)
    {
        PDI pdiEncontrado = pdiRepository.findByAlunoId(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Plano de Desenvolvimento Individual não encontrado"));

        return PDIMapper.toDTO(pdiEncontrado);
    }

    public Page<PDIResponseDTO> listarTodos(Pageable pageable)
    {
        Page<PDI> paginaDePDI = pdiRepository.findAll(pageable);

        return paginaDePDI.map(PDIMapper::toDTO);
    }

    public PDIResponseDTO atualizarPDI(Long id, PDIRequestDTO dto)
    {
        PDI pdiExistente = pdiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano de Desenvolvimento Individual não encontrado"));

        pdiExistente.setDataInicial(dto.getDataInicial());
        pdiExistente.setDataFim(dto.getDataFim());
        pdiExistente.setObjetivos(dto.getObjetivos());

        PDI pdiAtualizado = pdiRepository.save(pdiExistente);

        return PDIMapper.toDTO(pdiAtualizado);
    }

    public void deletarPDI(Long id)
    {
        PDI pdiParaDeletar = pdiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano de Desenvolvimento Individual não encontrado"));

        pdiRepository.delete(pdiParaDeletar);
    }
}
