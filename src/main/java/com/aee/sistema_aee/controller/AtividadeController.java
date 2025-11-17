package com.aee.sistema_aee.controller;

import com.aee.sistema_aee.dto.request.AtividadeRequestDTO;
import com.aee.sistema_aee.dto.response.AtividadeResponseDTO;
import com.aee.sistema_aee.service.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController
{
    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeResponseDTO criarAtividade(@Valid @RequestBody AtividadeRequestDTO dto)
    {
        return atividadeService.criarAtividade(dto);
    }

    @GetMapping("/{id}")
    public AtividadeResponseDTO buscarAtividadePorId(@PathVariable Long id)
    {
        return atividadeService.buscarPorId(id);
    }

    @GetMapping
    public List<AtividadeResponseDTO> listarTodasAtividades()
    {
        return atividadeService.listarTodos();
    }

    public AtividadeResponseDTO atualizarAtividade(@PathVariable Long id,
                                                   @Valid @RequestBody AtividadeRequestDTO dto)
    {
        return atividadeService.atualizarAtividade(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAtividade(@PathVariable Long id)
    {
        atividadeService.deletarAtividade(id);
    }
}
