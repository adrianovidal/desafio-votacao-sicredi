package br.com.votacao.share;

import br.com.votacao.share.enuns.StatusPermissao;

import java.io.Serializable;

public class StatusCpf implements Serializable {

    private StatusPermissao status;

    public StatusPermissao getStatus() {
        return status;
    }

    public void setStatus(StatusPermissao status) {
        this.status = status;
    }
}
