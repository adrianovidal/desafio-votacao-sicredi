package br.com.votacao.share.dto;

import br.com.votacao.share.enuns.ResultadoEnum;
import io.swagger.annotations.ApiModelProperty;

public class ResultadoDto {

    @ApiModelProperty(notes = "Identificação da pauta", required = true, name="idPauta")
    private Long idPauta;

    @ApiModelProperty(notes = "Identificação da Sessão", required = true, name="idSessao")
    private Long idSessao;

    @ApiModelProperty(readOnly = true)
    private Integer votosSim;

    @ApiModelProperty(readOnly = true)
    private Integer votosNao;

    @ApiModelProperty(readOnly = true)
    private Integer totalVotos;

    @ApiModelProperty(readOnly = true)
    private ResultadoEnum resultado;

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    public Integer getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(Integer votosSim) {
        this.votosSim = votosSim;
    }

    public Integer getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(Integer votosNao) {
        this.votosNao = votosNao;
    }

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }
}
