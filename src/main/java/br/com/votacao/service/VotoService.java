package br.com.votacao.service;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;

import java.util.List;

public interface VotoService {

    Voto votar(Voto voto);

    List<Voto> consultarVotos(Sessao sessao);
}
