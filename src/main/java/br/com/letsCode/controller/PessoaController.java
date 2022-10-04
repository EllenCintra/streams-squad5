package br.com.letsCode.controller;

import br.com.letsCode.dto.ObterListasPessoasRequest;
import br.com.letsCode.dto.CadastrarPessoaRequest;
import br.com.letsCode.dto.PessoaResponse;
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
    public ResponseEntity<Object> cadastrar(@RequestBody CadastrarPessoaRequest request) {
        service.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @GetMapping
    public ResponseEntity<Object> exibir(@RequestBody ObterListasPessoasRequest request) {
        PessoaResponse response = service.exibirInfos(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
