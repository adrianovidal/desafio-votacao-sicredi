package br.com.votacao.service.validator;

import br.com.votacao.domain.Sessao;

public interface ValidadorSessao {

    void validar(Sessao sessao);

    void validar(Long idSessao);
}
