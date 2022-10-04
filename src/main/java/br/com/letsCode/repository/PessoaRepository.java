package br.com.letsCode.repository;

import br.com.letsCode.enums.Geracao;
import br.com.letsCode.enums.Signo;
import br.com.letsCode.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    Stream<Pessoa> findBySignoAndIdade(Signo signo, Integer idade);

    @Query(value = "select * from Pessoa p where p.idade >= 18", nativeQuery = true)
    Stream<Pessoa> buscarMaiores18();

    Stream<Pessoa> findByGeracao(Geracao geracao);

    @Query(value = "select avg(p.idade) from Pessoa p", nativeQuery = true)
    float calcularIdadeMedia();

    @Query(value = "select sum(p.idade) from Pessoa p", nativeQuery = true)
    float calcularSomaIdades();

}
