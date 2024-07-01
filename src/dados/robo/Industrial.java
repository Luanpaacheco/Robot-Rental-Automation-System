package dados.robo;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("industrial")
public class Industrial extends Robo {
	private String setor;

	public Industrial(int id, String modelo, String setor) {
		super(id, modelo);
		this.setor = setor;
		setValorDiario(90);

	}


	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	@Override
	public double calculaLocacao(int dias) {
		return getValorDiario() * dias;
	}

	@Override
	public String toString() {
		return "\n" + super.toString() + "Setor = " + this.setor ;
	}
}
