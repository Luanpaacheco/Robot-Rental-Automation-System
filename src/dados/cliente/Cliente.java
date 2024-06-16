package dados.cliente;

public abstract class Cliente {

	private int codigo;

	private String nome;

	public abstract double calculaDesconto(int quantidadeRobos);

}
