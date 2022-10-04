package br.com.letsCode.service;

import br.com.letsCode.dto.PessoaProxCopaResponse;
import br.com.letsCode.dto.PessoaRequest;
import br.com.letsCode.dto.PessoaResponse;
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

    public void cadastrar (PessoaRequest request) {
        Pessoa pessoa = new Pessoa(request.getNome(), request.getCidadeNascimento(), request.getDataNascimento());
        repository.save(pessoa);
    }

    public PessoaResponse exibirInfos () {
        List<Pessoa> pessoas = repository.findAll();

        PessoaResponse response = new PessoaResponse();

        response.setPessoasPorSignoEIdade(buscarPorSignoEIdade(pessoas, Signo.Sagitário, 20));
        response.setMaioresDe18(buscarMaiores18(pessoas));
        response.setPessoasDaGeracao(buscarPorGeracao(pessoas, Geracao.Z));
        response.setPessoaMaisNova(buscarMaisNova(pessoas));
        response.setPessoaMaisVelha(buscarMaisVelha(pessoas));
        response.setMediaIdades(calcularMediaIdades(pessoas));
        response.setSomaIdades(calcularSomaIdades(pessoas));
        response.setIdadePessoasProxCopa(calcularIdadeProxCopa(pessoas));

        return response;
    }

    public List<Pessoa> buscarPorSignoEIdade (List<Pessoa> pessoas, Signo signo, int idade) {
        List<Pessoa> pessoasEncontradas = new ArrayList<>();

        pessoas.forEach(pessoa -> {
            if (pessoa.getSigno() == signo && pessoa.getIdade() > idade) {
                pessoasEncontradas.add(pessoa);
            }
        });

        return pessoasEncontradas;
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
