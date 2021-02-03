package br.com.votacao.cucumber.stepdefs.voto.chamadadireta;

import br.com.votacao.share.dto.VotoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class VotoChamadaDireta {

    @Autowired
    private MockMvc mockMvc;

    private final String URI_VOTO_CONTROLLER = "/api/votar";

    public ResultActions cadastrarVoto(VotoDto votoDto) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(URI_VOTO_CONTROLLER)
                .content(new ObjectMapper().writeValueAsString(votoDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

}
