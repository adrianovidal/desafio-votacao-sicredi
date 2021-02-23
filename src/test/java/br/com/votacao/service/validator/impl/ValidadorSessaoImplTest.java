package br.com.votacao.service.validator.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Pauta;
import br.com.votacao.domain.Sessao;
import br.com.votacao.fixture.PautaFixture;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.validator.ValidadorSessao;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.votacao.share.Constants.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorSessaoImplTest {

    private ValidadorSessao validadorSessao;

    @Mock private PautaRepository pautaRepositoryMock;
    @Mock private SessaoRepository sessaoRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Sessao sessao;
    private Pauta pauta;

    @Before
    public void inicializarContexto() {
        validadorSessao = new ValidadorSessaoImpl(pautaRepositoryMock, sessaoRepositoryMock);

        pauta = PautaFixture.umaPauta();

        sessao = SessaoFixture.umaSessao();
        sessao.setPauta(pauta);
    }

    @Test
    public void AoValidarComPautaNaoEncotradaDeveriaLancarExcecaoComAhMensagemEsperada() {
        when(pautaRepositoryMock.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        exception.expect(NegocioException.class);
        exception.expectMessage(PAUTA_NAO_LOCALIZADA_INFORMAR_VALOR_CORRETO_OU_CADADASTAR);

        validadorSessao.validar(sessao);
    }

    @Test
    public void AoValidarDadoQueExistaSessaoNaoDeveriaLancarExcecao() {
        when(pautaRepositoryMock.findById(sessao.obterIdPauta())).thenReturn(Optional.of(pauta));

        validadorSessao.validar(sessao);
    }

    @Test
    public void aoValidarDadoQueNaoExistaSessaoDeveriaLancarExecaoComMensagemEsperada() {
        when(sessaoRepositoryMock.findById(sessao.getId())).thenReturn(Optional.empty());

        exception.expect(NegocioException.class);
        exception.expectMessage(SESSAO_NAO_ENCONTRADA);

        validadorSessao.validar(sessao.getId());
    }

    @Test
    public void aoValidarDadoQueAhSessaoEstejaExpiradaDeveriaLancarExecaoComAhMensagemEsperada() {
        sessao.setDuracao(ZonedDateTime.now().minusMinutes(1L));

        when(sessaoRepositoryMock.findById(sessao.getId())).thenReturn(Optional.ofNullable(sessao));

        exception.expect(NegocioException.class);
        exception.expectMessage(A_SESSAO_INFORMADA_ESTA_FECHADA);

        validadorSessao.validar(sessao.getId());
    }

    @Test
    public void aoValidarDadoQueExistaSessaoAbertaNaoDeveriaLancarExececao() {
        when(sessaoRepositoryMock.findById(sessao.getId())).thenReturn(Optional.ofNullable(sessao));

        validadorSessao.validar(sessao.getId());
    }
}
