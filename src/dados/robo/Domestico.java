package dados.robo;

public class Domestico extends Robo{
	private int nivel;

	public Domestico(int id, String modelo, int nivel) {
		super(id, modelo);
		this.nivel = nivel;
		determinaValorDiario();
	}


	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public double calculaLocacao(int dias) {
		return getValorDiario() * dias;
	}

	@Override
	public String toString() {
		return "Informações do Robo Doméstico!\n" + super.toString() + "Nível = " + this.nivel;
	}
	public void determinaValorDiario() {
		if(nivel == 1){
			setValorDiario(10);
		}else if(nivel == 2){
			setValorDiario(20);
		}else {
			setValorDiario(50);
		}
	}
}
