package br.com.letsCode.dto;

import br.com.letsCode.enums.Geracao;
import br.com.letsCode.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaResponse {
    private List<Pessoa> maioresDe18;

    private List<Pessoa> pessoasGeracao;

    private List<PessoaProxCopaResponse> pessoasProxCopa;

    private Pessoa pessoaMaisNova;

    private Pessoa pessoaMaisVelha;

    private double mediaIdades;

    private int somaIdades;

}
