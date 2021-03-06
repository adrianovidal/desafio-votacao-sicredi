package br.com.votacao.share.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

public class DateStringConverterTest {

    protected DateStringConverter dateStringConverter;

    @Before
    public void inicializarContexto() {
        dateStringConverter = new DateStringConverter();
    }

    @Test
    public void dedveriaConverterDataParaString() {
        ZonedDateTime dataAgora = ZonedDateTime.now();
        String dataConvertida = dateStringConverter.convert(dataAgora);
        Assert.assertEquals(dataAgora.toString(), dataConvertida);
    }
}
