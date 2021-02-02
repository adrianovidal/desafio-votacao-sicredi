package br.com.votacao.share.converter;

import org.modelmapper.AbstractConverter;

import java.time.ZonedDateTime;

import static br.com.votacao.share.util.VerificadorUtil.estaNuloOuVazio;
import static java.lang.Long.parseLong;

public class StringDateConverter extends AbstractConverter<String, ZonedDateTime> {

    /**
     *
     * @param data - duração da sessão
     * @return Retona a hora de fechamento da sessão, caso não seja informando a duração da sessão, a mesma terá
     * duração de 1 minuto
     */
    @Override
    protected ZonedDateTime convert(String data) {
        if (estaNuloOuVazio(data)) {
            return ZonedDateTime.now().plusMinutes(1L);
        }

        return ZonedDateTime.now().plusMinutes(parseLong(data));
    }
}
