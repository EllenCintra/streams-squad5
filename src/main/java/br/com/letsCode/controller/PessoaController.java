package br.com.letsCode.controller;

import br.com.letsCode.dto.PessoaRequest;
import br.com.letsCode.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody PessoaRequest request) {
        service.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @GetMapping
    public ResponseEntity<Object> exibir() {
        service.exibirInfos();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
