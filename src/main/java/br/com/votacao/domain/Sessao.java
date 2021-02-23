package br.com.votacao.domain;

import br.com.votacao.share.enuns.TipoResultadoEnum;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

import static br.com.votacao.share.enuns.TipoResultadoEnum.FINAL;
import static br.com.votacao.share.enuns.TipoResultadoEnum.PARCIAL;
import static java.time.ZonedDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "SESSAO")
public class Sessao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idPauta")
    private Pauta pauta;

    private ZonedDateTime duracao;

    @Column(columnDefinition = "boolean default false")
    private boolean enviadoKafka;

    public Long getId() {
        return id;
    }

    public void setId(Long sequencial) {
        this.id = sequencial;
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
        return Objects.equals(id, sessao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public TipoResultadoEnum obterTipoResultado() {
        return now().isBefore(duracao) ?  PARCIAL : FINAL;
    }

    public Long obterIdPauta() {
        return getPauta().getId();
    }

    public boolean estaEncerrada() {
        return duracao.isBefore(now());
    }
}
