package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.ResultadoFixture;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.service.KafkaProducer;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.Resultado;
import br.com.votacao.unittest.UnitTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.util.List;

import static br.com.votacao.fixture.VotoFixture.umVotoNao;
import static br.com.votacao.fixture.VotoFixture.umVotoSim;
import static java.util.Arrays.asList;

public class ResultadoServiceImplTest extends UnitTest {

    @RunWith(Suite.class)
    @SuiteClasses({ })
    public static class AllTests {}

    @Mock protected SessaoService sessaoServiceMock;
    @Mock protected VotoService votoServiceMock;
    @Mock protected KafkaProducer kafkaProducerMock;

    protected ResultadoService resultadoService;

    protected Resultado resultado;
    protected Resultado resultadoEsperado;
    protected Sessao sessao;
    protected List<Voto> votos;
    protected String mensagem;

    @Before
    public void inicializarContexto() {
        resultadoService = new ResultadoServiceImpl(sessaoServiceMock, votoServiceMock, kafkaProducerMock);

        resultado = ResultadoFixture.umResultado();
        sessao = SessaoFixture.umaSessao();

        votos = asList(umVotoSim(1L), umVotoNao(2L), umVotoSim(3L), umVotoSim(4L), umVotoNao(5L));
        resultadoEsperado = ResultadoFixture.umResultadoFinal();
        converterEmJson();
    }

    @Test
    public void deveriaConsultarSessaoPeloIdEhPelaPauta() {
        contexto.checking(new Expectations(){{
            oneOf(sessaoServiceMock).consultar(with(same(resultado.getIdPauta())), with(same(resultado.getIdSessao())));
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
    public void deveriaRetornarOhResultadoDaVotacaoDaSessao() {
        permitirConsultarSessao();
        permitirConsultarVotosDaSessao();

        Resultado resultadoConsultado = consultarResultado();
        Assert.assertEquals(votos.size(), resultadoConsultado.getTotalVotos());
        Assert.assertEquals(3, resultadoConsultado.getVotoSim());
        Assert.assertEquals(2, resultadoConsultado.getVotosNao());
    }

    @Test
    public void deveriaEnviarResultadoParaOhKafkaProducer() {
        permitirConsultarSessao();
        permitirConsultarVotosDaSessao();

        contexto.checking(new Expectations(){{
            oneOf(kafkaProducerMock).writeMessage(with(equal(mensagem)));
        }});

        consultarResultado();
    }

    private Resultado consultarResultado() {
        return resultadoService.resultado(this.resultado);
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
            mensagem = ow.writeValueAsString(resultadoEsperado);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}