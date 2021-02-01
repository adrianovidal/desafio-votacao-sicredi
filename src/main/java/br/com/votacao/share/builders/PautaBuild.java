package br.com.votacao.share.builders;

import br.com.votacao.domain.Pauta;

public class PautaBuild {

    private final Pauta pauta;

    private PautaBuild() {
        pauta = new Pauta();
    }
    public static PautaBuild of() {
        return new PautaBuild();
    }

    public PautaBuild comNome(String nome) {
        this.pauta.setNome(nome);
        return this;
    }

    public Pauta build() {
        return pauta;
    }
}
