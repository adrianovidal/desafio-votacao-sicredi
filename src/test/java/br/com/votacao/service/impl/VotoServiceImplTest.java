package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.fixture.VotoFixture;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.unittest.UnitTest;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static br.com.votacao.share.Constants.O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO;

public class VotoServiceImplTest extends UnitTest {

    @Mock protected VerificarCpfAssociadoService verificarCpfAssociadoServiceMock;
    @Mock protected VotoRepository votoRepositoryMock;
    @Mock protected SessaoService sessaoServiceMock;

    protected VotoService votoService;

    protected Sessao sessao;
    protected Voto voto;
    protected Voto votoConsultado;
    protected Voto votoCadastrado;
    protected List<Voto> votos;

    @Before
    public void inicializarContexto() {
        votoService = new VotoServiceImpl(verificarCpfAssociadoServiceMock, votoRepositoryMock, sessaoServiceMock);

        sessao = SessaoFixture.umaSessao();

        voto = VotoFixture.umVoto();
        voto.setSessao(sessao);

        votoConsultado = null;
        votoCadastrado = new Voto();

        votos = new ArrayList<>();
    }

    @Test
    public void verificarAssociadoHaptoParaVotacao() {
        contexto.checking(new Expectations(){{
            oneOf(verificarCpfAssociadoServiceMock).verificar(with(same(voto.getAssociadoCpf())));
        }});

        permitirValidarSesscao();
        permitirConsultarVoto();
        permitirCadastrarVoto();

        votar();
    }

    @Test
    public void deveriaValidarSessao() {
        permitirVerificarAssociadoHaptoParaVotacao();

        contexto.checking(new Expectations(){{
            oneOf(sessaoServiceMock).validar(with(same(sessao)));
        }});

        permitirConsultarVoto();
        permitirCadastrarVoto();

        votar();
    }

    @Test
    public void deveriaCadastrarVoto() {
        permitirVerificarAssociadoHaptoParaVotacao();
        permitirValidarSesscao();
        permitirConsultarVoto();

        contexto.checking(new Expectations(){{
            oneOf(votoRepositoryMock).save(with(voto));
        }});

        votar();
    }

    @Test
    public void deveriaConsultarVoto() {
        permitirVerificarAssociadoHaptoParaVotacao();
        permitirValidarSesscao();

        contexto.checking(new Expectations(){{
            oneOf(votoRepositoryMock).findByAssociadoIdenAndSessao_Sequencial(with(same(voto.getAssociadoIden())),
                    with(same(voto.getSessao().getSequencial())));
            will(returnValue(votoConsultado));
        }});

        permitirCadastrarVoto();

        votar();
    }

    @Test
    public void deveriaLancarExcecaoParaVotoDoAssociadoNaMesmaSessao() {
        votoConsultado = new Voto();

        permitirVerificarAssociadoHaptoParaVotacao();
        permitirValidarSesscao();
        permitirConsultarVoto();

        contextoExcecao.expect(NegocioException.class);
        contextoExcecao.expectMessage(O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO);

        votar();
    }

    @Test
    public void deveriaConsultarOsVotosDaSessao() {
        contexto.checking(new Expectations(){{
            oneOf(votoRepositoryMock).findAllBySessao(with(same(sessao)));
            will(returnValue(votos));
        }});

        List<Voto> votosConsultados = votoService.consultarVotos(sessao);
        Assert.assertSame(votos, votosConsultados);
    }

    private void votar() {
        votoService.votar(voto);
    }

    void permitirVerificarAssociadoHaptoParaVotacao() {
        contexto.checking(new Expectations() {{
            allowing(verificarCpfAssociadoServiceMock).verificar(with(any(String.class)));
        }});
    }

    void permitirValidarSesscao() {
        contexto.checking(new Expectations() {{
            allowing(sessaoServiceMock).validar(with(any(Sessao.class)));
        }});
    }

    void permitirConsultarVoto() {
        contexto.checking(new Expectations() {{
            allowing(votoRepositoryMock).findByAssociadoIdenAndSessao_Sequencial(with(any(Long.class)), with(any(Long.class)));
            will(returnValue(votoConsultado));
        }});
    }

    void permitirCadastrarVoto() {
        contexto.checking(new Expectations() {{
            allowing(votoRepositoryMock).save(with(any(Voto.class)));
            will(returnValue(votoCadastrado));
        }});
    }
}