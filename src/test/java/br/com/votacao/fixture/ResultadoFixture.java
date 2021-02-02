package br.com.votacao.fixture;

import br.com.votacao.share.response.ResultadoResponse;

public class ResultadoFixture {

    public static ResultadoResponse umResultado() {
        ResultadoResponse resultadoResponse = new ResultadoResponse();
        resultadoResponse.setIdPauta(1L);
        resultadoResponse.setIdSessao(2L);
        return resultadoResponse;
    }

    public static ResultadoResponse umResultadoFinal() {
        ResultadoResponse resultadoResponse = umResultado();
        resultadoResponse.setVotosSim(3);
        resultadoResponse.setVotosNao(2);
        resultadoResponse.setTotalVotos(5);
        return resultadoResponse;
    }
}
