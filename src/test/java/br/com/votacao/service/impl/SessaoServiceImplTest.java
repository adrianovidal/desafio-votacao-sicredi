package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.SessaoService;
import br.com.votacao.unittest.UnitTest;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.votacao.share.Constants.SESSAO_NAO_ENCONTRADA_OU_ENCERRADA;
import static org.junit.Assert.assertSame;

public class SessaoServiceImplTest extends UnitTest {

    @RunWith(Suite.class)
    @SuiteClasses({ })
    public static class AllTests {}

    @Mock protected SessaoRepository sessaoRepositoryMock;

    protected SessaoService sessaoService;

    private Sessao sessao;
    private Sessao sessaoConsultada;

    private Long idSessao;
    private Long idPauta;

    private Optional<Sessao> optionalSessao;

    @Before
    public void inicializarContexto() {
        sessaoService = new SessaoServiceImpl(sessaoRepositoryMock);

        sessao = new Sessao();
        sessao.setSequencial(1L);

        sessaoConsultada = new Sessao();
        sessaoConsultada.setDuracao(ZonedDateTime.now().plusMinutes(2));
        optionalSessao = Optional.of(sessaoConsultada);

        idSessao = 1L;
        idPauta = 1L;
    }

    @Test
    public void deveriaRetornarSessaoCadastrada() {
        contexto.checking(new Expectations(){{
            oneOf(sessaoRepositoryMock).save(with(same(sessao)));
            will(returnValue(sessao));
        }});

        Sessao sessaoCadastrada = cadastrar();
        assertSame(sessao, sessaoCadastrada);
    }

    private Sessao cadastrar() {
        return sessaoService.cadastrar(sessao);
    }

    @Test
    public void deveriaConsultarSessao() {
        contexto.checking(new Expectations(){{
            oneOf(sessaoRepositoryMock).findById(with(equal(sessao.getSequencial())));
            will(returnValue(optionalSessao));
        }});

        validar();
    }

    @Test
    public void deveriaLancarExcecaoParaSessaoEncerrada() {
        sessaoConsultada.setDuracao(ZonedDateTime.now().minusMinutes(2));
        permitirConsultarSessao();

        contextoExcecao.expect(NegocioException.class);
        contextoExcecao.expectMessage(SESSAO_NAO_ENCONTRADA_OU_ENCERRADA);

        validar();
    }

    private void validar() {
        sessaoService.validar(sessao);
    }

    @Test
    public void deveriaConsultarSessaoPeloIdEhPeloPauta() {
        contexto.checking(new Expectations(){{
            oneOf(sessaoRepositoryMock).findBySequencialAndPauta_Id(with(same(idSessao)), with(same(idPauta)));
            will(returnValue(sessao));
        }});

        Sessao sessaoConsultada = sessaoService.consultar(idSessao, idPauta);
        assertSame(sessao, sessaoConsultada);
    }

    @Test
    public void deveriaAtualizarSessao() {
        contexto.checking(new Expectations(){{
            oneOf(sessaoRepositoryMock).save(with(same(sessao)));
        }});

        sessaoService.atualizar(sessao);
    }

    void permitirConsultarSessao() {
        contexto.checking(new Expectations() {{
            allowing(sessaoRepositoryMock).findById(with(any(Long.class)));
            will(returnValue(optionalSessao));
        }});
    }
}