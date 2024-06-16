package dados;

public enum Status {

	CADASTRADA("CADASTRADA"),
	EXECUTANDO("EXECUTANDO"),
	FINALIZADA("FINALIZADA"),
	CANCELADA("CANCELDA");

	private String nome;

	private Status(String nome){
		this.nome=nome;
	}

	public String getNome(){
		return nome;
	}

}
