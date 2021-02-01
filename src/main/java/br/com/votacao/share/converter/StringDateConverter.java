package br.com.votacao.share.converter;

import org.modelmapper.AbstractConverter;

import java.time.ZonedDateTime;

import static br.com.votacao.share.util.VerificadorUtil.estaNuloOuVazio;
import static java.lang.Long.parseLong;

public class StringDateConverter extends AbstractConverter<String, ZonedDateTime> {

    @Override
    protected ZonedDateTime convert(String data) {
        if (estaNuloOuVazio(data)) { return null; }

        return ZonedDateTime.now().plusMinutes(parseLong(data));
    }
}
