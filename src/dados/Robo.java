package dados;

public abstract class Robo {

	private int id;

	private String modelo;

	private double valorDiario;

	public abstract double calculaLocacao(int dias);

}
