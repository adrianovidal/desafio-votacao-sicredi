package br.com.votacao.controller;

import br.com.votacao.domain.Sessao;
import br.com.votacao.fixture.PautaFixture;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.service.SessaoService;
import br.com.votacao.share.converter.DateStringConverter;
import br.com.votacao.share.converter.StringDateConverter;
import br.com.votacao.share.dto.SessaoDto;
import br.com.votacao.unittest.UnitTest;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;

import static java.util.Date.from;

public class SessaoControllerTest extends UnitTest {

    @Mock protected SessaoService sessaoServiceMock;
    protected ModelMapper modelMapper;

    protected SessaoController sessaoController;

    protected SessaoDto sessaoDto;
    protected Sessao sessao;
    protected Sessao sessaoConsultada;

    @Before
    public void inicializarContexto() {
        modelMapper = new ModelMapper();
        modelMapper.addConverter(new StringDateConverter());
        modelMapper.addConverter(new DateStringConverter());

        sessaoController = new SessaoController(sessaoServiceMock, modelMapper);

        sessaoDto = new SessaoDto();
        sessaoDto.setDuracao("1");
        sessaoDto.setPautaId(1L);
        sessaoDto.setSequencial(1L);

        sessao = SessaoFixture.umaSessao();
        sessao.setPauta(PautaFixture.umaPauta());

        sessaoConsultada = new Sessao();
        sessaoConsultada.setSequencial(1L);
    }

    @Test
    public void deveriaCadastrarSessao() {
        contexto.checking(new Expectations(){{
            oneOf(sessaoServiceMock).cadastrar(with(matchesEntity(sessao)));
            will(returnValue(sessaoConsultada));
        }});

        cadastrar();
    }

    @Test
    public void deveriaRetornarAhSessao() {
        permitirCadastrarSessao();

        SessaoDto sessaoCadastradad = cadastrar();
        Assert.assertEquals(sessao.getSequencial(), sessaoCadastradad.getSequencial());
    }

    private SessaoDto cadastrar() {
        return sessaoController.save(sessaoDto);
    }

    void permitirCadastrarSessao() {
        contexto.checking(new Expectations() {{
            allowing(sessaoServiceMock).cadastrar(with(any(Sessao.class)));
            will(returnValue(sessaoConsultada));
        }});
    }

    public static Matcher<Sessao> matchesEntity(Sessao sessao) {
        return new TypeSafeMatcher<Sessao>() {
            @Override
            public boolean matchesSafely(Sessao objectToTest) {
                return from(objectToTest.getDuracao().toInstant()).toString().equals(from(sessao.getDuracao().toInstant()).toString())
                        && objectToTest.getSequencial().equals(sessao.getSequencial())
                        && objectToTest.getPauta().getId().equals(sessao.getPauta().getId());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("matchesSafely");
            }
        };
    }
}