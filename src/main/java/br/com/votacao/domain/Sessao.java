package br.com.votacao.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

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
}
