package br.com.votacao.cucumber.stepdefs.resultado;

import br.com.votacao.cucumber.stepdefs.StepDefs;
import br.com.votacao.cucumber.stepdefs.datatable.ResultadoDataTable;
import br.com.votacao.cucumber.stepdefs.resultado.chamadadireta.ResultadoChamadaDireta;
import br.com.votacao.share.dto.ResultadoDto;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static br.com.votacao.cucumber.stepdefs.resultado.verificador.ResultadoVerificador.verificarPautaCadastrada;

public class ResultadoStepDefs extends StepDefs {

    @Autowired
    ResultadoChamadaDireta resultadoChamadaDireta;

    @Before("@resultado_consulta")
    public void iniciarContexto() {
        limparBanco();
    }

    @Quando("^solicitar o resultado de uma Assembleia$")
    public void solicitarOResultadoDeUmaAssembleia() throws Exception {
        actions = resultadoChamadaDireta.obterResultado(criarResultado());
    }

    private ResultadoDto criarResultado() {
        ResultadoDto resultadoDto = new ResultadoDto();
        resultadoDto.setIdPauta(1L);
        resultadoDto.setIdSessao(1L);
        return resultadoDto;
    }

    @Então("^Retornar a seguine dados da votação$")
    public void retornarASeguineDadosDaVotacao(List<ResultadoDataTable> resultadoDataTables) {
        verificarPautaCadastrada(resultadoDataTables, obterObjetoRetornado(ResultadoDto.class));
    }
}
