package dados.cliente;

import aplicacao.ACMERobots;
import dados.Locacao;

public class Empresarial extends Cliente {
	ACMERobots acmeRobots = ACMERobots.getInstance();

	private int ano;

	public Empresarial(int codigo, String nome, int ano) {
		super(codigo, nome);
		this.ano = ano;
	}

	public int getAno() {
		return ano;
	}
	public int numeroRobosNoCliente() {
		int numeroRobos = 0;
		for(Locacao locacao : acmeRobots.getListaLocacoes()) {
			if(locacao.getCliente().getCodigo() == getCodigo()) {
				numeroRobos = locacao.getListaRobos().size();
			}
		}
		return numeroRobos;
	}
	@Override
	public double calculaDesconto() {
		double valor = 0;
		int numeroRobos = numeroRobosNoCliente();
		if(numeroRobos >=2 && numeroRobos<=9) {
			valor = 0.03;
		}
		if(numeroRobos >= 10) {
			valor = 0.07;
		}
		return valor;
	}

	@Override
	public String toString() {
		return super.toString() + "Ano: " + ano + ".";
	}
}
