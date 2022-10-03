package br.com.letsCode.controller;

import br.com.letsCode.dto.PessoaRequest;
import br.com.letsCode.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody PessoaRequest request) {
        service.cadastrarPessoa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
