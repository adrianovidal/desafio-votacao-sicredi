package br.com.votacao.share.enuns;

public enum TipoResultadoEnum {

    PARCIAL("Parcial"),
    FINAL("Final");

    private String descricao;

    TipoResultadoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
