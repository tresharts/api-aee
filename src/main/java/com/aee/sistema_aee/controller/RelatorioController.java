package com.aee.sistema_aee.controller;

import com.aee.sistema_aee.dto.request.RelatorioRequestDTO;
import com.aee.sistema_aee.dto.response.RelatorioResponseDTO;
import com.aee.sistema_aee.dto.response.ResponsavelResponseDTO;
import com.aee.sistema_aee.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController
{
    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RelatorioResponseDTO criarRelatorio(@Valid @RequestBody RelatorioRequestDTO dto)
    {
        return relatorioService.criarRelatorio(dto);
    }

    @GetMapping("/{id}")
    public RelatorioResponseDTO buscarRelatorioPorId(@PathVariable Long id)
    {
        return relatorioService.buscarPorId(id);
    }

    @GetMapping
    public List<RelatorioResponseDTO> listarTodosRelatorios()
    {
        return relatorioService.listarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRelatorio(@PathVariable Long id)
    {
        relatorioService.deletarRelatorio(id);
    }
}
