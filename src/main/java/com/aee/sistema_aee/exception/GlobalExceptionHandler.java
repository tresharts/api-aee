package com.aee.sistema_aee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException exception)
    {
        Map<String, String> erro = new HashMap<>();
        erro.put("status", "404 Not Found");
        erro.put("mensagem", exception.getMessage());

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityException exception)
    {
        Map<String, String> erro = new HashMap<>();
        erro.put("status", "409 Conflict");
        erro.put("mensagem", exception.getMessage());

        return new ResponseEntity<>(erro, HttpStatus.CONFLICT);
    }

    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception)
    {
        Map<String, String> errosDeCampo = new HashMap<>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(erro ->
        {
            errosDeCampo.put(erro.getField(), erro.getDefaultMessage());
        });

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", "400 Bad Request");
        resposta.put("mensagem", "Erro de validação");
        resposta.put("erros", errosDeCampo);

        return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
    }
}
