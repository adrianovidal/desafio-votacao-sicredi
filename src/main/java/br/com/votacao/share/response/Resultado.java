package br.com.votacao.share.response;

import br.com.votacao.share.enuns.TipoResultadoEnum;

public class Resultado {

    private Long idPauta;
    private Long idSessao;
    private Integer totalVotos;
    private Integer votosSim;
    private Integer votosNao;
    private TipoResultadoEnum tipoResultado;

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

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
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

    public TipoResultadoEnum getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(TipoResultadoEnum tipoResultado) {
        this.tipoResultado = tipoResultado;
    }
}
