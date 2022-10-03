package br.com.letsCode.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaRequest {

    private String nome;

    private String cidadeNascimento;

    private LocalDate dataNascimento;
}
