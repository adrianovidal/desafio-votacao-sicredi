package br.com.votacao.builder;

import br.com.votacao.share.dto.ResultadoDto;

public class ResultadoDtoBuilder {

    private final ResultadoDto resultadoDto;

    ResultadoDtoBuilder() {
        resultadoDto = new ResultadoDto();
    }

    public static ResultadoDtoBuilder of () {
        return new ResultadoDtoBuilder();
    }

    public static ResultadoDtoBuilder umaVotoDto() {
        return of().comVotosSim(3).comVotosNao(2).comTotalVotos(5);
    }

    public ResultadoDtoBuilder comVotosSim(Integer votosSim) {
        resultadoDto.setVotosSim(votosSim);
        return this;
    }

    public ResultadoDtoBuilder comVotosNao(Integer votosNao) {
        resultadoDto.setVotosNao(votosNao);
        return this;
    }

    public ResultadoDtoBuilder comTotalVotos(Integer totalVotos) {
        resultadoDto.setTotalVotos(totalVotos);
        return this;
    }

    public ResultadoDto build() {
        return resultadoDto;
    }
}
