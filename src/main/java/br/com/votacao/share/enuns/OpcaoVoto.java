package br.com.votacao.share.enuns;

public enum OpcaoVoto {

    SIM("Sim"),
    NAO("Não");

    private String descricao;

    OpcaoVoto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
