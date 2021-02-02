package br.com.votacao.controller;

import br.com.votacao.service.ResultadoService;
import br.com.votacao.share.dto.ResultadoDto;
import br.com.votacao.share.response.Resultado;
import br.com.votacao.unittest.UnitTest;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.modelmapper.ModelMapper;

public class ResultadoControllerTest extends UnitTest {

    @RunWith(Suite.class)
    @SuiteClasses({ })
    public static class AllTests { }

    @Mock protected ResultadoService resultadoServiceMock;
    protected ModelMapper modelMapper;

    protected ResultadoController resultadoController;

    protected ResultadoDto resultadoDto;
    protected Resultado resultado;
    protected Resultado resultadoConsultado;

    @Before
    public void inicializarContexto() {
        modelMapper = new ModelMapper();
        resultadoController = new ResultadoController(resultadoServiceMock, modelMapper);

        resultadoDto = new ResultadoDto();
        resultadoDto.setIdPauta(1L);
        resultadoDto.setIdSessao(2L);

        resultado = new Resultado();
        resultado.setIdPauta(1L);
        resultado.setIdSessao(2L);

        resultadoConsultado = new Resultado();
        resultadoConsultado.setTotalVotos(1);
    }

    @Test
    public void deveriaObterOhResultado() {
        contexto.checking(new Expectations(){{
            oneOf(resultadoServiceMock).resultado(with(matchesEntity(resultado)));
            will(returnValue(resultadoConsultado));
        }});

        cadastrar();
    }

    @Test
    public void deveriaRetornarOhResultaddoConsultado() {
        permitirConsultarResultado();

        ResultadoDto resultadoRetornado = cadastrar();
        Assert.assertEquals(resultadoConsultado.getTotalVotos(), resultadoRetornado.getTotalVotos());
    }

    private ResultadoDto cadastrar() {
        return resultadoController.save(resultadoDto);
    }

    void permitirConsultarResultado() {
        contexto.checking(new Expectations() {{
            allowing(resultadoServiceMock).resultado(with(any(Resultado.class)));
            will(returnValue(resultadoConsultado));
        }});
    }

    public static Matcher<Resultado> matchesEntity(Resultado resultado) {
        return new TypeSafeMatcher<Resultado>() {
            @Override
            public boolean matchesSafely(Resultado objectToTest) {
                return objectToTest.getIdPauta().equals(resultado.getIdPauta())
                        && objectToTest.getIdSessao().equals(resultado.getIdSessao());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("nome inválido");
            }
        };
    }
}