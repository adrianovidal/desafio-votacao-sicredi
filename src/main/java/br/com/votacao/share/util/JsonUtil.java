package br.com.votacao.share.util;

import br.com.votacao.share.Resultado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public interface JsonUtil {

    static String toJson(Resultado resultado) {
        ObjectWriter ow = new ObjectMapper().writer();
        String mensagem = null;
        try {
            mensagem = ow.writeValueAsString(resultado);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mensagem;
    }
}
