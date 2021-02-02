package br.com.votacao.share.enuns;

public enum ResultadoEnum {

    PARCIAL("Parcial"),
    FINAL("Final");

    private String descricao;

    ResultadoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
