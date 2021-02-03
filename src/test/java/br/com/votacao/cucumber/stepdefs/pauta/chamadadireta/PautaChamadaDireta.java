package br.com.votacao.cucumber.stepdefs.pauta.chamadadireta;

import br.com.votacao.share.dto.PautaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class PautaChamadaDireta {

    @Autowired
    private MockMvc mockMvc;

    private final String URI_PAUTA_CONTROLLER = "/api/pauta";

    public ResultActions cadastrarPauta(String nome) throws Exception {
        PautaDto pautaDto = new PautaDto() {{ setNome(nome); }};

        return mockMvc.perform(MockMvcRequestBuilders.post(URI_PAUTA_CONTROLLER)
                .content(new ObjectMapper().writeValueAsString(pautaDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }
}
