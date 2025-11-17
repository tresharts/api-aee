package com.aee.sistema_aee.controller;

import com.aee.sistema_aee.dto.request.ProfessorRequestDTO;
import com.aee.sistema_aee.dto.response.ProfessorResponseDTO;
import com.aee.sistema_aee.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController
{
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService)
    {
        this.professorService = professorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorResponseDTO criarProfessor(@Valid @RequestBody ProfessorRequestDTO dto)
    {
        return professorService.criarProfessor(dto);
    }

    @GetMapping("/{id}")
    public ProfessorResponseDTO buscarProfessorPorId(@PathVariable Long id)
    {
        return professorService.buscarProfessorPorId(id);
    }

    @GetMapping
    public List<ProfessorResponseDTO> listarTodosProfessores()
    {
        return professorService.listarTodos();
    }

    public ProfessorResponseDTO atualizarProfessor(@PathVariable Long id, @Valid @RequestBody ProfessorRequestDTO dto)
    {
        return professorService.atualizarProfessor(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfessor(@PathVariable Long id)
    {
        professorService.deletarProfessor(id);
    }
}
