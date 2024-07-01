package dados;

public enum Status {
	CADASTRADA("CADASTRADA"),
	EXECUTANDO("EXECUTANDO"),
	FINALIZADA("FINALIZADA"),
	CANCELADA("CANCELADA");

	private String nome;

	private Status(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public boolean permiteAlteracao() {
		return this == CADASTRADA || this == EXECUTANDO;
	}

//	public Status iniciarLocacao() {
//		if (this == CADASTRADA) {
//			return EXECUTANDO;
//		} else {
//			throw new IllegalStateException("Não é possível iniciar locação nesta situação.");
//		}
//	}

		public Status finalizarLocacao() {
		if (this == EXECUTANDO) {
			return FINALIZADA;
		} else {
			throw new IllegalStateException("Não é possível finalizar locação nesta situação.");
		}
	}

	public Status cancelarLocacao() {
		if (this == CADASTRADA || this == EXECUTANDO) {
			return CANCELADA;
		} else {
			throw new IllegalStateException("Não é possível cancelar locação nesta situação.");
		}
	}
}
