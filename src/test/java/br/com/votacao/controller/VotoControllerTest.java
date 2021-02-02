package br.com.votacao.controller;

import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.VotoFixture;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.dto.VotoDto;
import br.com.votacao.share.response.VotoResponse;
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
import org.springframework.http.ResponseEntity;

import static br.com.votacao.share.Constants.VOTO_CADASTRADO;

public class VotoControllerTest extends UnitTest {

    @Mock protected VotoService votoServiceMock;
    protected ModelMapper modelMapper;

    protected VotoController votoController;

    protected VotoDto votoDto;
    protected Voto voto;

    @Before
    public void inicializarContexto() {
        modelMapper = new ModelMapper();
        votoController = new VotoController(votoServiceMock, modelMapper);

        votoDto = new VotoDto();
        votoDto.setVoto("Sim");
        votoDto.setAssociadoIden(1L);

        voto = VotoFixture.umVoto();
        voto.setVoto("Sim");
    }

    @Test
    public void deveriaCadastrarSessao() {
        contexto.checking(new Expectations(){{
            oneOf(votoServiceMock).votar(with(matchesEntity(voto)));
        }});

        votoController.save(votoDto);
    }

    @Test
    public void deveriaRetornarAhSessao() {
        permitirCadastrarVoto();

        ResponseEntity<VotoResponse> confirmacaoVoto = votoController.save(votoDto);
        Assert.assertEquals(VOTO_CADASTRADO, confirmacaoVoto.getBody().getMessage());
    }

    void permitirCadastrarVoto() {
        contexto.checking(new Expectations() {{
            allowing(votoServiceMock).votar(with(any(Voto.class)));
        }});
    }

    public static Matcher<Voto> matchesEntity(Voto voto) {
        return new TypeSafeMatcher<Voto>() {
            @Override
            public boolean matchesSafely(Voto objectToTest) {
                return objectToTest.getVoto().equals(voto.getVoto())
                        && objectToTest.getAssociadoIden().equals(voto.getAssociadoIden());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("matchesSafely");
            }
        };
    }
}