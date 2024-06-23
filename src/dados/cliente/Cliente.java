package dados.cliente;

public abstract class Cliente {
	private int codigo;
	private String nome;

	public Cliente(int codigo, String nome){
		this.codigo=codigo;
		this.nome=nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome(){
		return nome;
	}

	@Override
	public String toString() {
		return "Cliente [" +
				" nome: " + nome+
				", codigo: " + codigo;
	}
}
