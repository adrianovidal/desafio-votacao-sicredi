package br.com.votacao.service.validator.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.service.validator.ValidadorSessao;
import org.springframework.stereotype.Component;

import static br.com.votacao.share.Constants.SESSAO_NAO_ENCONTRADA;
import static java.util.Optional.ofNullable;

@Component
public class ValidadorSessaoImpl implements ValidadorSessao {

    @Override
    public void validar(Sessao sessao) {
        ofNullable(sessao).orElseThrow(() -> new NegocioException(SESSAO_NAO_ENCONTRADA));
    }
}
