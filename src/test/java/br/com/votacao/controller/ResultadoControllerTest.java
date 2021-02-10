package br.com.votacao.controller;

import br.com.votacao.VotacaoApplication;
import br.com.votacao.builder.ResultadoDtoBuilder;
import br.com.votacao.fixture.ResultadoFixture;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.share.dto.ResultadoDto;
import br.com.votacao.share.response.Resultado;
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

import static br.com.votacao.share.ConstantsTests.URI_API_RESULTADO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoApplication.class)
@AutoConfigureMockMvc
public class ResultadoControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private ResultadoService resultadoServiceMock;

    @MockBean
    private ModelMapper modelMapperMock;

    protected ResultadoDto resultadoDto;
    protected Resultado resultado;
    protected Resultado resultadoConsultado;

    @Before
    public void inicializarContexto() {
        resultadoDto = ResultadoDtoBuilder.umaVotoDto().build();

        resultado = ResultadoFixture.umResultadoFinal();
    }

    @Test
    public void aoConsultarDeveriaRetornarOhResultaddoEsperada() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(ResultadoDto.class), eq(Resultado.class))).willReturn(resultado);
        BDDMockito.given(resultadoServiceMock.resultado(any(Resultado.class))).willReturn(resultado);
        BDDMockito.given(modelMapperMock.map(any(Resultado.class), eq(ResultadoDto.class))).willReturn(resultadoDto);

        ResultActions resultActions = consultarResultado();
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.votosSim").value(resultadoDto.getVotosSim()))
                .andExpect(jsonPath("$.votosNao").value(resultadoDto.getVotosNao()))
                .andExpect(jsonPath("$.totalVotos").value(resultadoDto.getTotalVotos()))
                .andExpect(jsonPath("$.resultado").value(resultadoDto.getResultado()));
    }

    private ResultActions consultarResultado() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(URI_API_RESULTADO)
                .content(new ObjectMapper().writeValueAsString(resultadoDto))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8));
    }
}
