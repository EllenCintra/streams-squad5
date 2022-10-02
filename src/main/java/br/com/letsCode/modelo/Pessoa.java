package br.com.letsCode.modelo;

import br.com.letsCode.enums.Geracao;
import br.com.letsCode.enums.Signo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Pessoa {
	@Id
	@GeneratedValue
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID id;
	public String nome;
	public String cidadeNascimento;
	public LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	public Signo signo;
	public Integer idade;
	@Enumerated(EnumType.STRING)
	public Geracao geracao;
}
