package dados;

import dados.robo.Robo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Locacao {

	private int numero;

	private List<Robo> listaRobos = new ArrayList<>();


	private Status situacao;

	private Date dataInicio;

	private int dataFim;

	public double calculaValorFinal() {
		return 0;
	}

}
