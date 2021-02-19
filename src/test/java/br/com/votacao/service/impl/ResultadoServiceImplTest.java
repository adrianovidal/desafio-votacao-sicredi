package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.ResultadoFixture;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.service.validator.ValidadorSessao;
import br.com.votacao.share.response.Resultado;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static br.com.votacao.fixture.ResultadoFixture.umResultado;
import static br.com.votacao.fixture.SessaoFixture.umaSessao;
import static br.com.votacao.fixture.VotoFixture.umVotoNao;
import static br.com.votacao.fixture.VotoFixture.umVotoSim;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultadoServiceImplTest {

    @Mock protected SessaoService sessaoServiceMock;
    @Mock protected VotoService votoServiceMock;
    @Mock protected ValidadorSessao validadorSessaoMock;

    protected ResultadoService resultadoService;

    protected Resultado resultado;
    protected Resultado resultadoEsperado;
    protected Sessao sessao;
    protected List<Voto> votos;

    @Before
    public void inicializarContexto() {
        resultadoService = new ResultadoServiceImpl(sessaoServiceMock, votoServiceMock, validadorSessaoMock);

        resultado = umResultado();
        resultadoEsperado = ResultadoFixture.umResultadoParcial();

        sessao = umaSessao();

        votos = asList(umVotoSim(1L), umVotoNao(2L), umVotoSim(3L), umVotoSim(4L), umVotoNao(5L));
    }

    @Test
    public void AoConsultarOhResultadoDeveriaValidar() {
        when(sessaoServiceMock.consultar(resultado.getIdPauta(), resultado.getIdSessao())).thenReturn(sessao);

        resultadoService.resultado(resultado);

        verify(validadorSessaoMock).validar(sessao);
        verify(votoServiceMock).consultarVotos(sessao);
    }

    @Test
    public void AoConsultarResultadoDeveriaRetornarOhResultadoDeUmaSessao() {
        when(sessaoServiceMock.consultar(resultado.getIdPauta(), resultado.getIdSessao())).thenReturn(sessao);
        when(votoServiceMock.consultarVotos(sessao)).thenReturn(votos);

        Resultado resultadoConsultado = resultadoService.resultado(this.resultado);
        Assert.assertEquals(resultadoEsperado.getVotosSim(), resultadoConsultado.getVotosSim());
        Assert.assertEquals(resultadoEsperado.getVotosNao(), resultadoConsultado.getVotosNao());
        Assert.assertEquals(resultadoEsperado.getTotalVotos(), resultadoConsultado.getTotalVotos());
        Assert.assertEquals(resultadoEsperado.getTipoResultado(), resultadoConsultado.getTipoResultado());
    }
}
