package br.com.votacao.cucumber.stepdefs.pauta.verificador;

import br.com.votacao.cucumber.stepdefs.datatable.PautaDataTable;
import br.com.votacao.domain.Pauta;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PautaVerificador {

    public static void verificarPautaCadastrada(Pauta pautaRetornado, List<PautaDataTable> pautaDataTables) {
        pautaDataTables.forEach(pautaDT -> {
            assertEquals(pautaRetornado.getId(), pautaDT.getId());
            assertEquals(pautaRetornado.getNome(), pautaDT.getNome());
        });
    }
}
