package com.aee.sistema_aee.controller;

import com.aee.sistema_aee.dto.request.RelatorioRequestDTO;
import com.aee.sistema_aee.dto.response.RelatorioResponseDTO;
import com.aee.sistema_aee.dto.response.ResponsavelResponseDTO;
import com.aee.sistema_aee.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<RelatorioResponseDTO> listarTodosRelatorios(@PageableDefault(size = 10, sort = "nome")
                                                                Pageable pageable)
    {
        return relatorioService.listarTodos(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRelatorio(@PathVariable Long id)
    {
        relatorioService.deletarRelatorio(id);
    }
}
