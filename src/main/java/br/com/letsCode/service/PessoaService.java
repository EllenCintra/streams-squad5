package br.com.letsCode.service;

import br.com.letsCode.dto.PessoaProxCopaResponse;
import br.com.letsCode.dto.PessoaRequest;
import br.com.letsCode.enums.Geracao;
import br.com.letsCode.model.Pessoa;
import br.com.letsCode.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public void cadastrar (PessoaRequest request) {
        Pessoa pessoa = new Pessoa(request.getNome(), request.getCidadeNascimento(), request.getDataNascimento());
        repository.save(pessoa);

        exibirInfos();
    }


    public void exibirInfos () {
        List<Pessoa> pessoas = repository.findAll();

        List<Pessoa> maioresDe18 = buscarMaiores18(pessoas);
        List<Pessoa> pessoasGeracao = buscarPorGeracao(pessoas, Geracao.Z);
        double mediaIdades = calcularMediaIdades(pessoas);
        int somaIdades = calcularSomaIdades(pessoas);
        List<PessoaProxCopaResponse> pessoasProxCopaResponse = calcularIdadeProxCopa(pessoas);
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

    public void buscarMaisNovaEMaisVelha (List<Pessoa> pessoas) {

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
