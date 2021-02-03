package br.com.votacao.cucumber.stepdefs.pauta;

import br.com.votacao.cucumber.stepdefs.StepDefs;
import br.com.votacao.cucumber.stepdefs.datatable.PautaDataTable;
import br.com.votacao.domain.Pauta;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

import java.util.List;

import static br.com.votacao.cucumber.stepdefs.pauta.verificador.PautaVerificador.verificarPautaCadastrada;

public class PautaStepDefs extends StepDefs {

    private String titulo;

    @Dado("^que foi informado o título \"([^\"]*)\"$")
    public void queFoiInformadoOTítulo(String titulo) {
        this.titulo = titulo;
    }

    @Quando("^solicitar cadastrar nova pauta$")
    public void solicitarCadastrarNovaPauta() throws Exception {
        actions = cadastrarPauta(titulo);
    }

    @Então("^Retornar a seguine pauta cadastrada$")
    public void retornarASeguinePautaCadastrada(List<PautaDataTable> pautaDataTables) {
        verificarPautaCadastrada(pautaDataTables, obterObjetoRetornado(Pauta.class));
    }
}
