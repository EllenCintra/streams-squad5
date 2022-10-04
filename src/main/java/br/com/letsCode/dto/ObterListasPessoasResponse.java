package br.com.letsCode.dto;

import br.com.letsCode.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObterListasPessoasResponse {
    private List<Pessoa> pessoasPorSignoEIdade;

    private List<Pessoa> maioresDe18;

    private List<Pessoa> pessoasDaGeracao;

    private List<PessoaProxCopaResponse> idadePessoasProxCopa;

    private Pessoa pessoaMaisNova;

    private Pessoa pessoaMaisVelha;

    private double mediaIdades;

    private int somaIdades;

}
