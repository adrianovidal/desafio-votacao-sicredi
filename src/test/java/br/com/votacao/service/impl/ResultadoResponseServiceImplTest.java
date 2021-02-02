package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.ResultadoFixture;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.response.ResultadoResponse;
import br.com.votacao.unittest.UnitTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static br.com.votacao.fixture.VotoFixture.umVotoNao;
import static br.com.votacao.fixture.VotoFixture.umVotoSim;
import static br.com.votacao.share.Constants.SESSAO_NAO_ENCONTRADA;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

public class ResultadoResponseServiceImplTest extends UnitTest {

    @Mock protected SessaoService sessaoServiceMock;
    @Mock protected VotoService votoServiceMock;

    protected ResultadoService resultadoService;

    protected ResultadoResponse resultadoResponse;
    protected ResultadoResponse resultadoResponseEsperado;
    protected Sessao sessao;
    protected List<Voto> votos;
    protected String mensagem;

    @Before
    public void inicializarContexto() {
        resultadoService = new ResultadoServiceImpl(sessaoServiceMock, votoServiceMock);

        resultadoResponse = ResultadoFixture.umResultado();
        resultadoResponseEsperado = ResultadoFixture.umResultadoFinal();

        sessao = SessaoFixture.umaSessao();

        votos = asList(umVotoSim(1L), umVotoNao(2L), umVotoSim(3L), umVotoSim(4L), umVotoNao(5L));
        converterEmJson();
    }

    @Test
    public void deveriaConsultarSessaoPeloIdEhPelaPauta() {
        contexto.checking(new Expectations(){{
            oneOf(sessaoServiceMock).consultar(with(same(resultadoResponse.getIdPauta())), with(same(resultadoResponse.getIdSessao())));
            will(returnValue(sessao));
        }});
        permitirConsultarVotosDaSessao();

        consultarResultado();
    }

    @Test
    public void deveriaConsultarOsVotosDaSessao() {
        permitirConsultarSessao();
        contexto.checking(new Expectations(){{
            oneOf(votoServiceMock).consultarVotos(with(same(sessao)));
            will(returnValue(votos));
        }});

        consultarResultado();
    }

    @Test
    public void deveriaLancarExcecaoParaSessaoNaoEncontrada() {
        sessao = null;
        permitirConsultarSessao();

        contextoExcecao.expect(NegocioException.class);
        contextoExcecao.expectMessage(SESSAO_NAO_ENCONTRADA);

        consultarResultado();
    }

    @Test
    public void deveriaRetornarOhResultadoDaVotacaoDaSessao() {
        permitirConsultarSessao();
        permitirConsultarVotosDaSessao();

        ResultadoResponse resultadoResponseConsultado = consultarResultado();
        Assert.assertEquals(converterInt(""+ votos.size()), resultadoResponseConsultado.getTotalVotos());
        Assert.assertEquals(converterInt("3"), resultadoResponseConsultado.getVotosSim());
        Assert.assertEquals(converterInt("2"), resultadoResponseConsultado.getVotosNao());
        Assert.assertEquals(converterInt("5"), resultadoResponseConsultado.getTotalVotos());
    }

    private Integer converterInt(String valor) {
        return parseInt(valor);
    }

    private ResultadoResponse consultarResultado() {
        return resultadoService.resultado(this.resultadoResponse);
    }

    void permitirConsultarSessao() {
        contexto.checking(new Expectations() {{
            allowing(sessaoServiceMock).consultar(with(any(Long.class)), with(any(Long.class)));
            will(returnValue(sessao));
        }});
    }

    void permitirConsultarVotosDaSessao() {
        contexto.checking(new Expectations() {{
            allowing(votoServiceMock).consultarVotos(with(any(Sessao.class)));
            will(returnValue(votos));
        }});
    }

    private void converterEmJson() {
        ObjectWriter ow = new ObjectMapper().writer();
        try {
            mensagem = ow.writeValueAsString(resultadoResponseEsperado);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}