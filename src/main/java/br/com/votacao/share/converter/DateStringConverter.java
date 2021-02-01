package br.com.votacao.share.converter;

import org.modelmapper.AbstractConverter;

import java.time.ZonedDateTime;

import static br.com.votacao.share.util.VerificadorUtil.estaNulo;

public class DateStringConverter extends AbstractConverter<ZonedDateTime, String> {

    @Override
    protected String convert(ZonedDateTime data) {
        if (estaNulo(data)) { return null; }

        return data.toString();
    }
}
