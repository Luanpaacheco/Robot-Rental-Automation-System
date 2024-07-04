package dados;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import dados.cliente.*;
import dados.robo.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Locacao {

	private int numero;
	private Status situacao;
	private Date dataInicio;
	private Date dataFim;
	private Cliente cliente;
	private List<Robo> listaDeRobos = new ArrayList<>();

	public Locacao(int numero, Status situacao, Date dataInicio, Date dataFim, Cliente cliente) {
		this.numero = numero;
		this.situacao = situacao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.cliente = cliente;
		this.listaDeRobos = listaDeRobos;
	}

	public int getNumero() {
		return numero;
	}

	public void adicionaRobos(Robo robo) {
		listaDeRobos.add(robo);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Robo> getListaRobos() {
		return listaDeRobos;
	}
	public Status getSituacao() {
		return situacao;
	}

	public Date getDataFim() {
		return dataFim;
	}

/*
	public Date dataNoFim() {
		Date dataFinal = dataInicio;
		return dataFinal;
	}

 */

	public void setSituacao(Status situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "\n" + "Locacao numero: " + numero + ";" +
				"\n" + "Cliente: " + cliente.getNome() + ";" +
				"\n" + "Situacao: " + situacao + ";" +
				"\n" + "Data Inicio: " + dataInicio + ";" +
				"\n" + "Data Fim: " + dataFim + " dias;" +
				"\n" + "Robos selecionados: " + listaDeRobos + ";";
	}


	public String toString2() {
		return "\n" + "Locacao numero: " + numero + ";" +
				"\n" + "Cliente: " + cliente.toString() + ";" +
				"\n" + "Situacao: " + situacao + ";" +
				"\n" + "Data Inicio: " + dataInicio + ";" +
				"\n" + "Data Fim: " + dataFim + " dias;" +
				"\n" + "Robos selecionados: " + listaDeRobos + ";";
	}
	public int getDiasEntreDatas() {
		long diffInMillis = Math.abs(dataFim.getTime() - dataInicio.getTime());
		return (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
	}

	public List<Robo> getListaDeRobos() {
		return listaDeRobos;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
}
