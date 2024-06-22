package dados.robo;

public class Domestico extends Robo{
	private int nivel;

	public Domestico(int id, String modelo, int nivel) {
		super(id, modelo);
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "Informações do Robo Doméstico!\n" + super.toString() + "Nível = " + this.nivel;
	}
}
