package com.aee.sistema_aee.controller;

import com.aee.sistema_aee.dto.request.PDIRequestDTO;
import com.aee.sistema_aee.dto.response.PDIResponseDTO;
import com.aee.sistema_aee.service.PDIService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pdi")
public class PDIController
{
    private final PDIService pdiService;

    public PDIController(PDIService pdiService)
    {
        this.pdiService = pdiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PDIResponseDTO criarPDI(@Valid @RequestBody PDIRequestDTO dto)
    {
        return pdiService.criarPDI(dto);
    }

    @GetMapping("/{id}")
    public PDIResponseDTO buscarPDIPorId(@PathVariable Long id)
    {
        return pdiService.buscarPorId(id);
    }

    public List<PDIResponseDTO> listarTodosPDIs()
    {
        return pdiService.listarTodos();
    }

    @GetMapping("/aluno/{alunoId}")
    public PDIResponseDTO buscarPDIPorAluno(@PathVariable Long alunoId)
    {
        return pdiService.buscarPorAlunoId(alunoId);
    }

    @PutMapping("/{id}")
    public PDIResponseDTO atualizarPDI(@PathVariable Long id,
                                       @Valid @RequestBody PDIRequestDTO dto)
    {
        return pdiService.atualizarPDI(id, dto);
    }
}
