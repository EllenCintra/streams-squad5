package br.com.letsCode.repository;

import br.com.letsCode.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
