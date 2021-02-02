package br.com.votacao.share.dto;

import io.swagger.annotations.ApiModelProperty;

public class PautaDto {

    @ApiModelProperty(readOnly = true)
    private Long id;

    @ApiModelProperty(notes = "Nome da pauta", required = true, name="nome")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
