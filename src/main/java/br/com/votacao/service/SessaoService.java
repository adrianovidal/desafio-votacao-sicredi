package br.com.votacao.service;

import br.com.votacao.domain.Sessao;

import java.util.List;

public interface SessaoService {

    Sessao cadastrar(Sessao sessao);

    Sessao consultar(Long idPauta, Long idSessaso);

    void atualizar(Sessao sessao);

    List<Sessao> consultarSessoesFinalizadasSemResultadoEnviado();
}
