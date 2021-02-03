package br.com.votacao.share.dto;

import io.swagger.annotations.ApiModelProperty;

public class SessaoDto {

    @ApiModelProperty(notes = "sequencial", name="id", hidden = true)
    private Long id;

    @ApiModelProperty(notes = "sequencial da pauta", name="pautaId", required=true)
    private Long pautaId;

    @ApiModelProperty(notes = "duração da sessão em minutos", name="duracao", required=true)
    private String duracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
