package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.validator.ValidadorSessao;
import br.com.votacao.unittest.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

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

    @Before
    public void inicializarContexto() {
        sessaoService = new SessaoServiceImpl(sessaoRepositoryMock, validadorSessaoMock);

        sessao = SessaoFixture.umaSessao();

    }

    @Test
    public void deveriaValidarAhSessaoIhCadastrar() {
        Mockito.when(sessaoRepositoryMock.save(sessao)).thenReturn(sessao);

        sessaoService.cadastrar(sessao);

        verify(validadorSessaoMock).validar(sessao);
    }

    @Test
    public void deveriaRetornarSessaoCadastrada() {
        Mockito.when(sessaoRepositoryMock.save(sessao)).thenReturn(sessao);

        Sessao sessaoCadastrada = sessaoService.cadastrar(sessao);

        assertSame(sessao, sessaoCadastrada);
    }
}
