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

	private String nome;

	private String cidadeNascimento;

	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private Signo signo;

	private Integer idade;

	@Enumerated(EnumType.STRING)
	private Geracao geracao;

	public Pessoa (String nome, String cidadeNascimento, LocalDate dataNascimento) {
		this.nome = nome;
		this.cidadeNascimento = cidadeNascimento;
		this.dataNascimento = dataNascimento;
		this.signo = verificarSigno(MonthDay.from(dataNascimento));
		this.geracao = definirGeracao(Year.from(dataNascimento));
		this.idade = calcularIdade(dataNascimento);
	}

	public Signo verificarSigno(MonthDay aniversario) {
		MonthDay leaoComecaEm = MonthDay.of(7,22);
		MonthDay leaoTerminaEm = MonthDay.of(8,23);

		MonthDay sagitarioComecaEm = MonthDay.of(11,21);
		MonthDay sagitarioTerminaEm = MonthDay.of(12,22);

		MonthDay aquarioComecaEm = MonthDay.of(01,21);
		MonthDay aquarioTerminaEm = MonthDay.of(02,19);

		MonthDay cancerComecaEm = MonthDay.of(06,22);
		MonthDay cancerTerminaEm = MonthDay.of(07,22);

		MonthDay escorpiaoComecaEm = MonthDay.of(10,23);		MonthDay escorpiaoTerminaEm = MonthDay.of(11,21);

		if (verificarSeEstaEntreDatas(aniversario, leaoComecaEm, leaoTerminaEm)) return Signo.Leão;

		if (verificarSeEstaEntreDatas(aniversario, sagitarioComecaEm, sagitarioTerminaEm)) return Signo.Sagitário;

		if (verificarSeEstaEntreDatas(aniversario, aquarioComecaEm, aquarioTerminaEm)) return Signo.Aquário;

		if (verificarSeEstaEntreDatas(aniversario, escorpiaoComecaEm, escorpiaoTerminaEm)) return Signo.Escorpião;

		if (verificarSeEstaEntreDatas(aniversario, cancerComecaEm, cancerTerminaEm)) return Signo.Câncer;

		return null;
	}

	private static boolean verificarSeEstaEntreDatas(MonthDay dataParaVerificar, MonthDay dataInicio, MonthDay dataFim) {
		return !(dataParaVerificar.isBefore(dataInicio) || dataParaVerificar.isAfter(dataFim)) ;
	}

	public Geracao definirGeracao(Year anoDeNascimento) {
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

		return null;
	}

	private static boolean verificarSeEstaEntreAnos(Year anoParaVerificar, Year anoInicio, Year anoFim) {
		return !(anoParaVerificar.isBefore(anoInicio) || anoParaVerificar.isAfter(anoFim));
	}

	private Integer calcularIdade(LocalDate dataNascimento){
		return Period.between(dataNascimento, LocalDate.now()).getYears();
	}

}
