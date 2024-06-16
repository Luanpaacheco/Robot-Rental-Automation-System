package dados.robo;

public class Agricola extends Robo {
	private double area;
	private String uso;

	public Agricola(int id, String modelo, double valorDiario, double area, String uso) {
		super(id, modelo, valorDiario);
		this.area = area;
		this.uso = uso;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}
}