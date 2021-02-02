package br.com.votacao.controller;

import br.com.votacao.domain.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.share.dto.PautaDto;
import br.com.votacao.unittest.UnitTest;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class PautaControllerTest extends UnitTest {

    @Mock protected PautaRepository pautaRepositoryMock;

    protected ModelMapper modelMapperMock = new ModelMapper();

    protected PautaController pautaController;

    private PautaDto pautaDto;
    private Pauta pauta;
    private Pauta pautaRetornado;

    @Before
    public void inicializarContexto() {
        pautaController = new PautaController(pautaRepositoryMock, modelMapperMock);

        String licitacao = "Licitação";
        pautaDto = new PautaDto();
        pautaDto.setNome(licitacao);

        pauta = new Pauta();
        pauta.setNome(licitacao);

        pautaRetornado = new Pauta();
        pautaRetornado.setNome(licitacao);
    }

    @Test
    public void deveriaConverterDtoEmEntidade() {
        contexto.checking(new Expectations(){{
            oneOf(pautaRepositoryMock).save(with(matchesEntity(pauta)));
            will(returnValue(pautaRetornado));
        }});

        cadastrar();
    }

    @Test
    public void deveriaRetornarPautaDto() {
        permitirDeveriaSalvarPauta();

        PautaDto pautaDtoRetornada = cadastrar();
        Assert.assertEquals(pautaDto.getNome(), pautaDtoRetornada.getNome());
    }

    private PautaDto cadastrar() {
        return pautaController.save(pautaDto);
    }

    void permitirDeveriaSalvarPauta() {
        contexto.checking(new Expectations() {{
            allowing(pautaRepositoryMock).save(with(any(Pauta.class)));
            will(returnValue(pautaRetornado));
        }});
    }

    public static Matcher<Pauta> matchesEntity(Pauta pauta) {
        return new TypeSafeMatcher<Pauta>() {
            @Override
            public boolean matchesSafely(Pauta objectToTest) {
                return objectToTest.getNome().equals(pauta.getNome());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("nome inválido");
            }
        };
    }
}