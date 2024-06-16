package dados.robo;

public  class Robo {
	private int id;
	private String modelo;
	private double valorDiario;

	public Robo(int id, String modelo, double valorDiario) {
		this.id = id;
		this.modelo = modelo;
		this.valorDiario = valorDiario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getValorDiario() {
		return valorDiario;
	}

	public void setValorDiario(double valorDiario) {
		this.valorDiario = valorDiario;
	}

	@Override
	public String toString() {
		return "Robo{" +
				"id=" + id +
				", modelo='" + modelo + '\'' +
				", valorDiario=" + valorDiario +
				'}';
	}
}