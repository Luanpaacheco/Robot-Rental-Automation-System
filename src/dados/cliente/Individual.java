package dados.cliente;


import aplicacao.ACMERobots;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import dados.Locacao;

@JsonTypeName("individual")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Individual extends Cliente{
	private String cpf;
	ACMERobots acmeRobots = ACMERobots.getInstance();

	public Individual(int codigo, String nome, String cpf){
		super(codigo,nome);
		this.cpf=cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public int numeroRobosNoCliente() {
		int numeroRobos = 0;
		for(Locacao locacao : acmeRobots.getListaLocacoes())
            if (locacao.getCliente().getNome().equals(getNome())) {
                numeroRobos = locacao.getListaRobos().size();
            }
		return numeroRobos;
	}
	public double calculaDesconto() {
		double valor = 0;
		int numeroRobos = numeroRobosNoCliente();
		if(numeroRobos > 1) {
			valor = 0.05;
		}
		return valor;
	}
	@Override
	public String toString() {
		return super.toString() + "CPF: " + cpf + ".";
	}
}
