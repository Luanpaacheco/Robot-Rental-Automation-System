package dados.robo;

public class Agricola extends Robo{
	private double area;
	private String uso;

	public Agricola(int id, String modelo, double area, String uso) {
		super(id, modelo);
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

	@Override
	public String toString() {
		return "Informações do Robo Agricola!\n" + super.toString() + "Uso = " + this.uso + " | Area = " + this.area + " metros quadrados";
	}
}
