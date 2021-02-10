package br.com.votacao.controller;

import br.com.votacao.VotacaoApplication;
import br.com.votacao.builder.PautaDtoBuilder;
import br.com.votacao.domain.Pauta;
import br.com.votacao.fixture.PautaFixture;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.share.dto.PautaDto;
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

import static br.com.votacao.share.ConstantsTests.URI_API_PAUTA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoApplication.class)
@AutoConfigureMockMvc
public class PautaControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private PautaRepository pautaRepositoryMock;

    @MockBean
    private ModelMapper modelMapperMock;

    private PautaDto pautaDto;
    private Pauta pauta;
    private Pauta pautaRetornado;

    @Before
    public void inicializarContexto() {
        String licitacao = "Licitação";
        pauta = PautaFixture.umaPauta();
        pautaDto = PautaDtoBuilder.of()
                .comNome(licitacao).build();
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhPautaEsperada() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(PautaDto.class), eq(Pauta.class))).willReturn(pauta);
        BDDMockito.given(pautaRepositoryMock.save(any(Pauta.class))).willReturn(pauta);
        BDDMockito.given(modelMapperMock.map(any(Pauta.class), eq(PautaDto.class))).willReturn(pautaDto);

        ResultActions resultActions = cadastrarPauta();
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(pautaDto.getNome()));
    }

    private ResultActions cadastrarPauta() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(URI_API_PAUTA)
                .content(new ObjectMapper().writeValueAsString(pautaDto))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8));
    }
}
