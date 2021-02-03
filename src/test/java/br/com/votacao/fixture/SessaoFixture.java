package br.com.votacao.fixture;

import br.com.votacao.domain.Sessao;

import java.time.ZonedDateTime;

public class SessaoFixture {

    public static Sessao umaSessao() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setEnviadoKafka(false);
        sessao.setDuracao(ZonedDateTime.now().plusMinutes(1));
        return sessao;
    }
}
