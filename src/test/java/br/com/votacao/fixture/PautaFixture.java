package br.com.votacao.fixture;

import br.com.votacao.domain.Pauta;

public class PautaFixture {

    public static Pauta umaPauta() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        return pauta;
    }
}
