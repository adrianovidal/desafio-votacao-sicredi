package br.com.votacao.share;

public class Resultado {

    private Long idPauta;
    private Long idSessao;
    private int totalVotos;
    private int votoSim;
    private int votosNao;

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

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    public int getVotoSim() {
        return votoSim;
    }

    public void setVotoSim(int votoSim) {
        this.votoSim = votoSim;
    }

    public int getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(int votosNao) {
        this.votosNao = votosNao;
    }
}
