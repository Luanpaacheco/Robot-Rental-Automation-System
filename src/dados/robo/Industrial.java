package dados.robo;

import javax.swing.plaf.RootPaneUI;

public class Industrial extends Robo {
	private String setor;

	public Industrial(int id, String modelo, String setor) {
		super(id, modelo);
		this.setor = setor;

	}


	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	@Override
	public double calculaLocacao(int dias) {
		setValorDiario(90);
		return getValorDiario() * dias;
	}

	@Override
	public String toString() {
		return "Informações do Robo Industrial!\n" + super.toString() + "Setor = " + this.setor ;
	}
}
