package br.com.votacao.share.enuns;

import java.util.HashMap;
import java.util.Map;

import static br.com.votacao.share.Constants.ASSOCIADO_CPF_INVALIDO;
import static br.com.votacao.share.Constants.ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR;

public enum StatusPermissao {

    ABLE_TO_VOTE,
    UNABLE_TO_VOTE,
    CPF_INVALIDO;

    public static Map<StatusPermissao, String> mapStatusPermissao;

    static {
        mapStatusPermissao = new HashMap<>();
        mapStatusPermissao.put(UNABLE_TO_VOTE, ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR);
        mapStatusPermissao.put(CPF_INVALIDO, ASSOCIADO_CPF_INVALIDO);
    }
}
