package br.com.votacao.share.builders;

import br.com.votacao.share.response.VotoResponse;

import static br.com.votacao.share.Constants.VOTO_CADASTRADO;

public class VotoResponseBuild {

    private final VotoResponse votoResponse;

    private VotoResponseBuild() {
        votoResponse = new VotoResponse();
    }

    public static VotoResponseBuild of() {
        return new VotoResponseBuild();
    }

    public static VotoResponse votoCadastrado() {
        return of().comMensagem(VOTO_CADASTRADO).build();
    }

    public VotoResponseBuild comMensagem(String mensagem) {
        this.votoResponse.setMessage(mensagem);
        return this;
    }

    public VotoResponse build() {
        return votoResponse;
    }
}
