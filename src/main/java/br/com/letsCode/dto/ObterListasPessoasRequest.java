package br.com.letsCode.dto;

import br.com.letsCode.enums.Geracao;
import br.com.letsCode.enums.Signo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObterListasPessoasRequest {

    private Signo signo;
    private int idade;
    private Geracao geracao;
}
