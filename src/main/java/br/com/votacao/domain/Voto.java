package br.com.votacao.domain;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "VOTO")
public class Voto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "voto")
    private String voto;

    @ManyToOne
    @JoinColumn(name = "idSessao", unique = true)
    private Sessao sessao;

    @Column(name = "idAssociado", unique = true)
    private Long associadoIden;

    @Column(name = "cpfAssociado")
    private String associadoCpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Long getAssociadoIden() {
        return associadoIden;
    }

    public void setAssociadoIden(Long idAssociado) {
        this.associadoIden = idAssociado;
    }

    public String getAssociadoCpf() {
        return associadoCpf;
    }

    public void setAssociadoCpf(String cpf) {
        this.associadoCpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(sessao.getSequencial(), voto.sessao.getSequencial()) &&
                Objects.equals(associadoIden, voto.associadoIden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessao.getSequencial(), associadoIden);
    }
}
