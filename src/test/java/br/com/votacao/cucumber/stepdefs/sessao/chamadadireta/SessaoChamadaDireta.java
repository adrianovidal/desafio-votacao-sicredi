package br.com.votacao.cucumber.stepdefs.sessao.chamadadireta;

import br.com.votacao.share.dto.SessaoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class SessaoChamadaDireta {

    @Autowired
    private MockMvc mockMvc;

    private final String URI_SESSAO_CONTROLLER = "/api/sessao";

    public ResultActions cadastrarSessao(SessaoDto sessaoDto) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(URI_SESSAO_CONTROLLER)
                .content(new ObjectMapper().writeValueAsString(sessaoDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }
}
