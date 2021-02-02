package br.com.votacao.domain;

import br.com.votacao.share.enuns.ResultadoEnum;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

import static br.com.votacao.share.enuns.ResultadoEnum.FINAL;
import static br.com.votacao.share.enuns.ResultadoEnum.PARCIAL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "SESSAO")
public class Sessao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long sequencial;

    @OneToOne
    @JoinColumn(name = "idPauta")
    private Pauta pauta;

    private ZonedDateTime duracao;

    @Column(columnDefinition = "boolean default false")
    private boolean enviadoKafka;

    public Long getSequencial() {
        return sequencial;
    }

    public void setSequencial(Long sequencial) {
        this.sequencial = sequencial;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public ZonedDateTime getDuracao() {
        return duracao;
    }

    public void setDuracao(ZonedDateTime duracao) {
        this.duracao = duracao;
    }

    public boolean isEnviadoKafka() {
        return enviadoKafka;
    }

    public void setEnviadoKafka(boolean enviadoKafka) {
        this.enviadoKafka = enviadoKafka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessao sessao = (Sessao) o;
        return Objects.equals(sequencial, sessao.sequencial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequencial);
    }

    public ResultadoEnum obterTipoResultado() {
        return ZonedDateTime.now().isBefore(duracao) ?  PARCIAL : FINAL;
    }
}
