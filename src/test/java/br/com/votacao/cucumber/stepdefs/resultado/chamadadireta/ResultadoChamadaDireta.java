package br.com.votacao.cucumber.stepdefs.resultado.chamadadireta;

import br.com.votacao.share.dto.ResultadoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class ResultadoChamadaDireta {

    @Autowired
    private MockMvc mockMvc;

    private final String URI_RESULTADO_CONTROLLER = "/api/resultado";

    public ResultActions obterResultado(ResultadoDto resultadoDto) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(URI_RESULTADO_CONTROLLER)
                .content(new ObjectMapper().writeValueAsString(resultadoDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }
}
