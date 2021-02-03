package br.com.votacao.share.dto;

import io.swagger.annotations.ApiModelProperty;

public class VotoDto {

    @ApiModelProperty(notes = "Voto (Sim, Não)", required = true, name="voto")
    private String voto;

    @ApiModelProperty(notes = "Identificação da sessão", required = true, name="sessaoId")
    private Long sessaoId;

    @ApiModelProperty(notes = "Identificação do associado", required = true, name="associadoIden")
    private Long associadoIden;

    @ApiModelProperty(notes = "CPF do associado", required = true, name="associadoCpf")
    private String associadoCpf;

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public Long getAssociadoIden() {
        return associadoIden;
    }

    public void setAssociadoIden(Long associadoIden) {
        this.associadoIden = associadoIden;
    }

    public String getAssociadoCpf() {
        return associadoCpf;
    }

    public void setAssociadoCpf(String associadoCpf) {
        this.associadoCpf = associadoCpf;
    }
}
