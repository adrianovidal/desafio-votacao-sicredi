package br.com.votacao.share.dto;

public class VotoDto {

    private String voto;
    private Long sessaoSequencial;
    private Long associadoIden;
    private String associadoCpf;

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public Long getSessaoSequencial() {
        return sessaoSequencial;
    }

    public void setSessaoSequencial(Long sessaoSequencial) {
        this.sessaoSequencial = sessaoSequencial;
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
