package br.com.letsCode.service;

import br.com.letsCode.dto.ObterListasPessoasRequest;
import br.com.letsCode.dto.PessoaProxCopaResponse;
import br.com.letsCode.dto.CadastrarPessoaRequest;
import br.com.letsCode.dto.ObterListasPessoasResponse;
import br.com.letsCode.enums.Geracao;
import br.com.letsCode.enums.Signo;
import br.com.letsCode.model.Pessoa;
import br.com.letsCode.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public void cadastrar (CadastrarPessoaRequest request) {
        Pessoa pessoa = new Pessoa(request.getNome(), request.getCidadeNascimento(), request.getDataNascimento());
        repository.save(pessoa);
    }

    public ObterListasPessoasResponse exibirInfos (ObterListasPessoasRequest request) {
        List<Pessoa> pessoas = repository.findAll();

        ObterListasPessoasResponse response = new ObterListasPessoasResponse();

        response.setPessoasPorSignoEIdade(buscarPorSignoEIdade(pessoas, request.getSigno(), request.getIdade()));
        response.setMaioresDe18(buscarMaiores18(pessoas));
        response.setPessoasDaGeracao(buscarPorGeracao(pessoas, request.getGeracao()));
        response.setIdadePessoasProxCopa(calcularIdadeProxCopa(pessoas));
        response.setPessoaMaisVelha(buscarMaisVelha(pessoas));
        response.setPessoaMaisNova(buscarMaisNova(pessoas));
        response.setMediaIdades(calcularMediaIdades(pessoas));
        response.setSomaIdades(calcularSomaIdades(pessoas));

        return response;
    }

    public List<Pessoa> buscarPorSignoEIdade (List<Pessoa> pessoas, Signo signo, int idade) {
        List<Pessoa> pessoasPorSignoEIdade = pessoas.stream()
                .filter(p -> p.getSigno() == signo && p.getIdade() > idade)
                .collect(Collectors.toList());

        return pessoasPorSignoEIdade;
    }

    public List<Pessoa> buscarMaiores18 (List<Pessoa> pessoas) {
        List<Pessoa> maioresDeIdade = pessoas.stream()
                .filter(p -> p.getIdade() >= 18)
                .collect(Collectors.toList());
        return maioresDeIdade;
    }

    public List<Pessoa> buscarPorGeracao (List<Pessoa> pessoas, Geracao geracao) {
        return pessoas.stream()
                .filter(p -> p.getGeracao() == geracao)
                .collect(Collectors.toList());
    }

    public List<PessoaProxCopaResponse> calcularIdadeProxCopa (List<Pessoa> pessoas) {
        LocalDate dataProxCopa =  LocalDate.of(2026, 06, 8);

        List<PessoaProxCopaResponse> pessoasProxCopa = new ArrayList<>();

        pessoas.forEach(pessoa -> {
            int idadePessoaProxCopa = Period.between(pessoa.getDataNascimento(), dataProxCopa).getYears();
            pessoasProxCopa.add(new PessoaProxCopaResponse(pessoa.getNome(), idadePessoaProxCopa)) ;
        });

        return pessoasProxCopa;
    }

    Comparator<Pessoa> comparadorDeIdades = Comparator.comparing( Pessoa::getIdade);

    public Pessoa buscarMaisNova (List<Pessoa> pessoas) {
        return pessoas.stream().min(comparadorDeIdades).get();
    }

    public Pessoa buscarMaisVelha (List<Pessoa> pessoas) {
        return pessoas.stream().max(comparadorDeIdades).get();
    }

    public double calcularMediaIdades (List<Pessoa> pessoas) {
        return pessoas.stream()
                .mapToDouble(pessoa -> pessoa.getIdade().doubleValue())
                .average().getAsDouble();
    }

    public int calcularSomaIdades (List<Pessoa> pessoas) {
        return pessoas.stream()
                      .mapToInt(pessoa -> pessoa.getIdade())
                      .sum();
    }
}
