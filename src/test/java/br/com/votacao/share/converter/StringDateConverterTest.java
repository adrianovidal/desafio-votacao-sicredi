package br.com.votacao.share.converter;

import br.com.votacao.unittest.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static java.lang.Long.parseLong;
import static java.time.ZonedDateTime.now;
import static java.util.Date.from;

public class StringDateConverterTest extends UnitTest {

    protected StringDateConverter stringDateConverter;

    @Before
    public void inicializarContexto() {
        stringDateConverter = new StringDateConverter();
    }

    @Test
    public void deveriaConverterStringDuracaoParaData() {
        ZonedDateTime dataEncerramentoSessao = stringDateConverter.convert("2");
        Assert.assertEquals(obterDuracao("2"), from(dataEncerramentoSessao.toInstant()).toString());
    }

    @Test
    public void dedveriaConveterParaDuracaoPadraoDeUmMinuto() {
        ZonedDateTime dataEncerramentoSessao = stringDateConverter.convert("");
        Assert.assertEquals(obterDuracao("1"), from(dataEncerramentoSessao.toInstant()).toString());
    }

    private String obterDuracao(String duracao) {
        Date date = from(now().plusMinutes(parseLong(duracao)).toInstant());
        return date.toString();
    }
}