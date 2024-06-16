package dados;

public enum SituacaoLocacao {
    CADASTRADA,
    EXECUTANDO,
    FINALIZADA,
    CANCELADA;

    public boolean permiteAlteracao() {
        return this == CADASTRADA || this == EXECUTANDO;
    }

    public SituacaoLocacao iniciarLocacao() {
        if (this == CADASTRADA) {
            return EXECUTANDO;
        } else {
            throw new IllegalStateException("Não é possível iniciar locação nesta situação.");
        }
    }

    public SituacaoLocacao finalizarLocacao() {
        if (this == EXECUTANDO) {
            return FINALIZADA;
        } else {
            throw new IllegalStateException("Não é possível finalizar locação nesta situação.");
        }
    }

    public SituacaoLocacao cancelarLocacao() {
        if (this == CADASTRADA || this == EXECUTANDO) {
            return CANCELADA;
        } else {
            throw new IllegalStateException("Não é possível cancelar locação nesta situação.");
        }
    }
}