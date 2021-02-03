package br.com.votacao.cucumber.stepdefs;

import br.com.votacao.VotacaoApplication;
import br.com.votacao.VotacaoApplicationTests;
import br.com.votacao.domain.Pauta;
import br.com.votacao.share.dto.PautaDto;
import br.com.votacao.share.dto.SessaoDto;
import br.com.votacao.share.dto.VotoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.votacao.share.ConstantsTests.MAPPER;
import static br.com.votacao.share.ConstantsTests.TYPE_FACTORY;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {VotacaoApplication.class, VotacaoApplicationTests.class})
public abstract class StepDefs {

    @Autowired
    private MockMvc mockMvc;

    public PautaDto pautaDto;
    public SessaoDto sessaoDto;

    public ResultActions actions;

    private String URI_PAUTA_CONTROLLER = "/api/pauta";
    private String URI_SESSAO_CONTROLLER = "/api/sessao";
    private String URI_VOTO_CONTROLLER = "/api/votar";
    private String URI_RESULTADO_CONTROLLER = "/api/resultado";

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URI_PAUTA_CONTROLLER)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions cadastrarPauta(String nome) throws Exception {
        PautaDto pautaDto = new PautaDto() {{ setNome(nome); }};

        return mockMvc.perform(MockMvcRequestBuilders.post(URI_PAUTA_CONTROLLER)
                .content(new ObjectMapper().writeValueAsString(pautaDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    public ResultActions cadastrarSessao(SessaoDto sessaoDto) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(URI_SESSAO_CONTROLLER)
                .content(new ObjectMapper().writeValueAsString(sessaoDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    public ResultActions cadastrarVoto(VotoDto votoDto) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(URI_VOTO_CONTROLLER)
                .content(new ObjectMapper().writeValueAsString(votoDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    protected <T> T obterObjetoRetornado(Class<T> clazz) {
        try {
            return asJsonToClass(obterResposta().getContentAsString(), clazz);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static <T> T asJsonToClass(final String json, Class<T> classe) {
        try {
            return MAPPER.readValue(json, TYPE_FACTORY.constructType(classe));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MockHttpServletResponse obterResposta() {
        return actions.andReturn().getResponse();
    }
}
