package br.com.votacao.share.builders;

import br.com.votacao.share.Resultado;
import br.com.votacao.share.enuns.ResultadoEnum;

public class ResultadoBuild {

    private final Resultado resultado;

    private ResultadoBuild() {
        resultado = new Resultado();
    }

    public static ResultadoBuild of() {
        return new ResultadoBuild();
    }

    public ResultadoBuild comIdSessao(Long idSessao) {
        this.resultado.setIdSessao(idSessao);
        return this;
    }

    public ResultadoBuild comIdPauta(Long idPauta) {
        this.resultado.setIdPauta(idPauta);
        return this;
    }

    public ResultadoBuild comVotosSim(Integer votosSim) {
        this.resultado.setVotosSim(votosSim);
        return this;
    }

    public ResultadoBuild comVotosNao(Integer votosNao) {
        this.resultado.setVotosNao(votosNao);
        return this;
    }

    public ResultadoBuild comTotalVotos(Integer totalVoto) {
        this.resultado.setTotalVotos(totalVoto);
        return this;
    }

    public ResultadoBuild comResultado(ResultadoEnum resultadoEnum) {
        this.resultado.setResultado(resultadoEnum);
        return this;
    }

    public Resultado build() {
        return resultado;
    }
}
