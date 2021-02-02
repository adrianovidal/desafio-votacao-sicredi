package br.com.votacao.cucumber.stepdefs.sessao.verificador;

import br.com.votacao.cucumber.stepdefs.datatable.SessaoDataTable;
import br.com.votacao.share.dto.SessaoDto;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessaoVerificador {

    public static void verificarPautaCadastrada(SessaoDto sessaoDtoRetornada, List<SessaoDataTable> sessaoDataTables) {
        sessaoDataTables.forEach(sessaoDT -> {
            assertEquals(sessaoDtoRetornada.getSequencial(), sessaoDT.getId());
            assertEquals(sessaoDtoRetornada.getPautaId(), sessaoDT.getIdPauta());
            assertTrue(validarTempoDeDuracaoDaSessao(sessaoDT.getDuracao(), sessaoDT.getDuracao(), sessaoDtoRetornada.getDuracao()));
        });
    }

    public static boolean validarTempoDeDuracaoDaSessao(String duracao, String dataEsperada, String dataRetoranada) {
        ZonedDateTime dataR = ZonedDateTime.parse(dataRetoranada);
        ZonedDateTime dataE = ZonedDateTime.now();
        long diferenca = ChronoUnit.SECONDS.between(dataE, dataR);
        return diferenca > (Long.parseLong(duracao)*60);
    }

    static SimpleDateFormat simpleDateFormat(String formato) {
        return new SimpleDateFormat(formato);
    }
}
