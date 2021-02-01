package br.com.votacao.share.builders;

import br.com.votacao.share.StatusCpf;
import br.com.votacao.share.enuns.StatusPermissao;

import static br.com.votacao.share.enuns.StatusPermissao.UNABLE_TO_VOTE;

public class StatusCpfBuild {

    private final StatusCpf statusCpf;

    private StatusCpfBuild() {
        statusCpf = new StatusCpf();
    }

    public static StatusCpfBuild of() {
        return new StatusCpfBuild();
    }

    public static StatusCpf associadoIncapazDeVoto() {
        return of().comStatus(UNABLE_TO_VOTE).build();
    }

    public StatusCpfBuild comStatus(StatusPermissao statusPermissao) {
        this.statusCpf.setStatus(statusPermissao);
        return this;
    }

    public StatusCpf build() {
        return statusCpf;
    }
}
