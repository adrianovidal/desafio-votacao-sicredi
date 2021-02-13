package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.fixture.PautaFixture;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.validator.ValidadorSessao;
import br.com.votacao.unittest.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.time.ZonedDateTime.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessaoServiceImplTest extends UnitTest {

    @Mock
    protected SessaoRepository sessaoRepositoryMock;

    @Mock
    protected PautaRepository pautaRepositoryMock;

    @Mock
    protected ValidadorSessao validadorSessaoMock;

    protected SessaoService sessaoService;

    private Sessao sessao;
    private List<Sessao> sessoesEsperadas;

    @Before
    public void inicializarContexto() {
        sessaoService = new SessaoServiceImpl(sessaoRepositoryMock, validadorSessaoMock);

        sessao = SessaoFixture.umaSessao();
        sessao.setPauta(PautaFixture.umaPauta());
        sessoesEsperadas = new ArrayList<>();
    }

    @Test
    public void deveriaValidarAhSessaoIhCadastrar() {
        when(sessaoRepositoryMock.save(sessao)).thenReturn(sessao);

        cadastrar();

        verify(validadorSessaoMock).validar(sessao);
    }

    @Test
    public void deveriaRetornarSessaoCadastrada() {
        when(sessaoRepositoryMock.save(sessao)).thenReturn(sessao);

        Sessao sessaoCadastrada = cadastrar();

        assertSame(sessao, sessaoCadastrada);
    }

    private Sessao cadastrar() {
        return sessaoService.cadastrar(sessao);
    }

    @Test
    public void deveriaAtualizarSessao() {
        when(sessaoRepositoryMock.save(sessao)).thenReturn(sessao);

        sessaoService.atualizar(sessao);
    }

    @Test
    public void deveriaConsultarSessoesEnceradasNaoEnviadasPeloKafka() {
        when(sessaoRepositoryMock.consultarSessoesFinalizadasSemResultado(now())).thenReturn(sessoesEsperadas);

        List<Sessao> sessoesRetornadas = sessaoService.consultarSessoesFinalizadasSemResultadoEnviado();

        assertEquals(sessoesEsperadas, sessoesRetornadas);
    }

    @Test
    public void deveriaConsultarAhSessao() {
        when(sessaoRepositoryMock.findByIdAndPauta_Id(sessao.getId(), sessao.getPauta().getId())).thenReturn(sessao);

        Sessao sessaoConsultada = sessaoService.consultar(sessao.getId(), sessao.getPauta().getId());

        Assert.assertSame(sessao, sessaoConsultada);
    }
}
