package br.com.votacao.fixture;

import br.com.votacao.share.Resultado;

public class ResultadoFixture {

    public static Resultado umResultado() {
        Resultado resultado = new Resultado();
        resultado.setIdPauta(1L);
        resultado.setIdSessao(2L);
        return resultado;
    }

    public static Resultado umResultadoFinal() {
        Resultado resultado = umResultado();
        resultado.setVotosSim(3);
        resultado.setVotosNao(2);
        resultado.setTotalVotos(5);
        return resultado;
    }
}
