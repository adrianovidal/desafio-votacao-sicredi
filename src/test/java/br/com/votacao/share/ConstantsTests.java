package br.com.votacao.share;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;

public interface ConstantsTests {

    ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule()).enable(ACCEPT_SINGLE_VALUE_AS_ARRAY);
    TypeFactory TYPE_FACTORY = MAPPER.getTypeFactory();
}
