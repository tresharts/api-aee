package com.aee.sistema_aee.controller;

import com.aee.sistema_aee.dto.request.ResponsavelRequestDTO;
import com.aee.sistema_aee.dto.response.ResponsavelResponseDTO;
import com.aee.sistema_aee.service.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController
{
    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsavelResponseDTO criarResponsavel(@Valid @RequestBody ResponsavelRequestDTO dto)
    {
        return responsavelService.criarResponsavel(dto);
    }

    @GetMapping("/{id}")
    public ResponsavelResponseDTO buscarResponsavelPorId(@PathVariable Long id)
    {
        return responsavelService.buscarPorId(id);
    }

    @GetMapping
    public Page<ResponsavelResponseDTO> listarTodosResponsaveis(@PageableDefault(size = 10, sort = "nome")
                                                                    Pageable pageable)
    {
        return responsavelService.listarTodos(pageable);
    }

    public ResponsavelResponseDTO atualizarResponsavel(@PathVariable Long id,
                                                       @Valid @RequestBody ResponsavelRequestDTO dto)
    {
        return responsavelService.atualizarResponsavel(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarResponsavel(@PathVariable Long id)
    {
        responsavelService.deletarResponsavel(id);
    }
}
