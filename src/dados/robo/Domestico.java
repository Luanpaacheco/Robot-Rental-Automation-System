package dados.robo;

public class Domestico extends Robo {
	private int nivel;

	public Domestico(int id, String modelo, double valorDiario, int nivel) {
		super(id, modelo, valorDiario);
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}