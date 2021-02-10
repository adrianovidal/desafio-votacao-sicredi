package br.com.votacao.controller;

import br.com.votacao.VotacaoApplication;
import br.com.votacao.domain.Sessao;
import br.com.votacao.fixture.PautaFixture;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.service.SessaoService;
import br.com.votacao.share.dto.SessaoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.votacao.builder.SessaoDtoBuilder.umaSessaoDto;
import static br.com.votacao.share.ConstantsTests.URI_API_SESSAO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = VotacaoApplication.class)
public class SessaoControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected SessaoService sessaoServiceMock;

    @MockBean
    protected ModelMapper modelMapperMock;

    protected SessaoDto sessaoDto;
    protected Sessao sessao;

    @Before
    public void inicializarContexto() {
        sessaoDto = umaSessaoDto().build();

        sessao = SessaoFixture.umaSessao();
        sessao.setPauta(PautaFixture.umaPauta());
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhSessaoEsperada() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(SessaoDto.class), eq(Sessao.class))).willReturn(sessao);
        BDDMockito.given(sessaoServiceMock.cadastrar(any(Sessao.class))).willReturn(sessao);
        BDDMockito.given(modelMapperMock.map(any(Sessao.class), eq(SessaoDto.class))).willReturn(sessaoDto);

        ResultActions resultActions = cadastrarSessao();
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pautaId").value(sessaoDto.getPautaId()))
                .andExpect(jsonPath("$.duracao").value(sessaoDto.getDuracao()));
    }

    private ResultActions cadastrarSessao() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(URI_API_SESSAO)
                .content(new ObjectMapper().writeValueAsString(sessaoDto))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8));
    }
}
