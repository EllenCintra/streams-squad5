package br.com.letsCode.service;

import br.com.letsCode.dto.PessoaRequest;
import br.com.letsCode.enums.Geracao;
import br.com.letsCode.enums.Signo;
import br.com.letsCode.model.Pessoa;
import br.com.letsCode.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public void cadastrarPessoa (PessoaRequest request) {
        Pessoa pessoa = new Pessoa(request.getNome(), request.getCidadeNascimento(), request.getDataNascimento());

        LocalDate dataNascimento = pessoa.getDataNascimento();

        pessoa.setSigno(pessoa.verificarSigno(MonthDay.from(dataNascimento)));
        pessoa.setGeracao(pessoa.definirGeracao(Year.from(dataNascimento)));
        pessoa.setIdade(pessoa.calcularIdade(dataNascimento));

        repository.save(pessoa);
    }

}
