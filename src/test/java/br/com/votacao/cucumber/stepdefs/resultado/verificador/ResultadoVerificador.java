package br.com.votacao.cucumber.stepdefs.resultado.verificador;

import br.com.votacao.cucumber.stepdefs.datatable.ResultadoDataTable;
import br.com.votacao.share.dto.ResultadoDto;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResultadoVerificador {

    public static void verificarPautaCadastrada(List<ResultadoDataTable> resultadoDataTables, ResultadoDto resultadoRetornado) {
        resultadoDataTables.forEach(resultaddoDT -> {
            assertEquals(resultaddoDT.getVotosSim(), resultadoRetornado.getVotosSim());
            assertEquals(resultaddoDT.getVotosNao(), resultadoRetornado.getVotosNao());
            assertEquals(resultaddoDT.getTotalVotos(), resultadoRetornado.getTotalVotos());
            assertEquals(resultaddoDT.getResultado(), resultadoRetornado.getResultado().name());
        });
    }
}
