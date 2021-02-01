package br.com.votacao.fixture;

import br.com.votacao.domain.Sessao;

public class SessaoFixture {

    public static Sessao umaSessao() {
        Sessao sessao = new Sessao();
        sessao.setSequencial(1L);
        return sessao;
    }
}
