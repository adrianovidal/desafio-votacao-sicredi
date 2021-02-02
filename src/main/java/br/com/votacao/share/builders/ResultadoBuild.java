package br.com.votacao.share.builders;

import br.com.votacao.share.response.ResultadoResponse;
import br.com.votacao.share.enuns.ResultadoEnum;

public class ResultadoBuild {

    private final ResultadoResponse resultadoResponse;

    private ResultadoBuild() {
        resultadoResponse = new ResultadoResponse();
    }

    public static ResultadoBuild of() {
        return new ResultadoBuild();
    }

    public ResultadoBuild comIdSessao(Long idSessao) {
        this.resultadoResponse.setIdSessao(idSessao);
        return this;
    }

    public ResultadoBuild comIdPauta(Long idPauta) {
        this.resultadoResponse.setIdPauta(idPauta);
        return this;
    }

    public ResultadoBuild comVotosSim(Integer votosSim) {
        this.resultadoResponse.setVotosSim(votosSim);
        return this;
    }

    public ResultadoBuild comVotosNao(Integer votosNao) {
        this.resultadoResponse.setVotosNao(votosNao);
        return this;
    }

    public ResultadoBuild comTotalVotos(Integer totalVoto) {
        this.resultadoResponse.setTotalVotos(totalVoto);
        return this;
    }

    public ResultadoBuild comResultado(ResultadoEnum resultadoEnum) {
        this.resultadoResponse.setResultado(resultadoEnum);
        return this;
    }

    public ResultadoResponse build() {
        return resultadoResponse;
    }
}
