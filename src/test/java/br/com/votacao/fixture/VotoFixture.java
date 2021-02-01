package br.com.votacao.fixture;

import br.com.votacao.domain.Voto;

public class VotoFixture {

    public static Voto umVoto() {
        Voto voto = new Voto();
        voto.setAssociadoIden(1L);
        voto.setAssociadoCpf("07568173070");
        return voto;
    }

    public static Voto umVotoSim(Long idVoto) {
        Voto voto = new Voto();
        voto.setId(idVoto);
        voto.setVoto("Sim");
        return voto;
    }

    public static Voto umVotoNao(Long idVoto) {
        Voto voto = new Voto();
        voto.setId(idVoto);
        voto.setVoto("Não");
        return voto;
    }
}
