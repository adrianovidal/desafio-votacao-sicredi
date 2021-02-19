package br.com.votacao.fixture;

import br.com.votacao.share.response.Resultado;

import static br.com.votacao.share.enuns.TipoResultadoEnum.PARCIAL;

public class ResultadoFixture {

    public static Resultado umResultado() {
        Resultado resultado = new Resultado();
        resultado.setIdPauta(1L);
        resultado.setIdSessao(2L);
        return resultado;
    }

    public static Resultado umResultadoParcial() {
        Resultado resultado = umResultado();
        resultado.setVotosSim(3);
        resultado.setVotosNao(2);
        resultado.setTotalVotos(5);
        resultado.setTipoResultado(PARCIAL);
        return resultado;
    }
}
