package com.aee.sistema_aee.controller;

import com.aee.sistema_aee.dto.request.AlunoRequestDTO;
import com.aee.sistema_aee.dto.response.AlunoResponseDTO;
import com.aee.sistema_aee.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController
{
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponseDTO criarAluno(@Valid @RequestBody AlunoRequestDTO dto)
    {
        return alunoService.criarAluno(dto);
    }

    @GetMapping("/{id}")
    public AlunoResponseDTO buscarAlunoPorId(@PathVariable Long id)
    {
        return alunoService.buscarPorId(id);
    }

    @GetMapping
    public List<AlunoResponseDTO> listarTodosAlunos()
    {
        return alunoService.listarTodos();
    }

    @PutMapping("/{id}")
    public AlunoResponseDTO atualizarAluno(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO dto)
    {
        return alunoService.atualizarAluno(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable Long id)
    {
        alunoService.deletarAluno(id);
    }
}
