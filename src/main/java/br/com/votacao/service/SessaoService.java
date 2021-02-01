package br.com.votacao.service;

import br.com.votacao.domain.Sessao;

public interface SessaoService {

    void validar(Sessao sessao);

    Sessao cadastrar(Sessao sessao);
}
