package br.com.votacao.share.dto;

import io.swagger.annotations.ApiModelProperty;

public class SessaoDto {

    @ApiModelProperty(notes = "sequencial", name="sequencial", hidden = true)
    private Long sequencial;

    @ApiModelProperty(notes = "sequencial da pauta", name="pautaId", required=true)
    private Long pautaId;

    @ApiModelProperty(notes = "duração da sessão", name="duracao", required=true)
    private String duracao;

    public Long getSequencial() {
        return sequencial;
    }

    public void setSequencial(Long sequencial) {
        this.sequencial = sequencial;
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
