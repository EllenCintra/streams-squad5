package br.com.letsCode.model;

import br.com.letsCode.enums.Geracao;
import br.com.letsCode.enums.Signo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
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

	public static String verificarSigno(MonthDay aniversario) {
		MonthDay leaoComecaEm = MonthDay.of(7,22);
		MonthDay leaoTerminaEm = MonthDay.of(8,23);

		MonthDay sagitarioComecaEm = MonthDay.of(11,21);
		MonthDay sagitarioTerminaEm = MonthDay.of(12,22);

		if (verificarSeEstaEntreDatas(aniversario, leaoComecaEm, leaoTerminaEm)) return "Leão";

		if (verificarSeEstaEntreDatas(aniversario, sagitarioComecaEm, sagitarioTerminaEm)) return "Sagitário";

		return "Ainda não foi cadastrado um signo para a data informada";
	}


	private static boolean verificarSeEstaEntreDatas(MonthDay dataParaVerificar, MonthDay dataInicio, MonthDay dataFim) {
		return !(dataParaVerificar.isBefore(dataInicio) || dataParaVerificar.isAfter(dataFim)) ;
	}

	public static Geracao definirGeracao(Year anoDeNascimento) {
		Year boomerInicio = Year.of(1940);
		Year boomerFim = Year.of(1960);

		Year xInicio = Year.of(1961);
		Year xFim = Year.of(1980);

		Year yInicio = Year.of(1981);
		Year yFim = Year.of(1995);

		Year zInicio = Year.of(1996);
		Year zFim = Year.of(2010);

		if (verificarSeEstaEntreAnos(anoDeNascimento, boomerInicio, boomerFim)) return Geracao.Boomer;

		if (verificarSeEstaEntreAnos(anoDeNascimento, xInicio, xFim)) return Geracao.X;

		if (verificarSeEstaEntreAnos(anoDeNascimento, yInicio, yFim)) return Geracao.Y;

		if (verificarSeEstaEntreAnos(anoDeNascimento, zInicio, zFim)) return Geracao.Z;

		throw new RuntimeException();
	}

	private static boolean verificarSeEstaEntreAnos(Year anoParaVerificar, Year anoInicio, Year anoFim) {
		return !(anoParaVerificar.isBefore(anoInicio) || anoParaVerificar.isAfter(anoFim));
	}

	public static String calcularIdade(LocalDate dataNascimento){
		return Period.between(LocalDate.now(), dataNascimento).toString().substring(2);
	}


}
