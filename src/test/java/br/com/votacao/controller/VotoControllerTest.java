package br.com.votacao.controller;

import br.com.votacao.VotacaoApplication;
import br.com.votacao.builder.VotoDtoBuilder;
import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.VotoFixture;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.dto.VotoDto;
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

import static br.com.votacao.share.ConstantsTests.URI_API_VOTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = VotacaoApplication.class)
public class VotoControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected VotoService votoServiceMock;

    @MockBean
    protected ModelMapper modelMapperMock;

    protected VotoDto votoDto;
    protected Voto voto;

    @Before
    public void inicializarContexto() {
        votoDto = VotoDtoBuilder.umaVotoDto().build();
        voto = VotoFixture.umVotoSim(1L);
    }

    @Test
    public void aoCadastrarDeveriaRetornarOhVotoEsperado() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(VotoDto.class), eq(Voto.class))).willReturn(voto);
        BDDMockito.given(votoServiceMock.votar(any(Voto.class))).willReturn(voto);
        BDDMockito.given(modelMapperMock.map(any(Voto.class), eq(VotoDto.class))).willReturn(votoDto);

        ResultActions resultActions = cadastrarVoto();
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.voto").value(votoDto.getVoto()))
                .andExpect(jsonPath("$.associadoIden").value(votoDto.getAssociadoIden()))
                .andExpect(jsonPath("$.sessaoId").value(votoDto.getSessaoId()))
                .andExpect(jsonPath("$.associadoCpf").value(votoDto.getAssociadoCpf()));
    }

    private ResultActions cadastrarVoto() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(URI_API_VOTO)
                .content(new ObjectMapper().writeValueAsString(votoDto))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8));
    }
}
