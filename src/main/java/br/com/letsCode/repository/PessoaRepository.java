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

}
