package br.com.letsCode.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaProxCopaResponse {
    private String nome;
    private int idade;

    public PessoaProxCopaResponse(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
