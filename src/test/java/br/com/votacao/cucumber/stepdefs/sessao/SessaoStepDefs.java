package br.com.votacao.cucumber.stepdefs.sessao;

import br.com.votacao.cucumber.stepdefs.StepDefs;
import br.com.votacao.cucumber.stepdefs.datatable.SessaoDataTable;
import br.com.votacao.cucumber.stepdefs.pauta.chamadadireta.PautaChamadaDireta;
import br.com.votacao.cucumber.stepdefs.sessao.chamadadireta.SessaoChamadaDireta;
import br.com.votacao.share.dto.PautaDto;
import br.com.votacao.share.dto.SessaoDto;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static br.com.votacao.cucumber.stepdefs.sessao.verificador.SessaoVerificador.verificarPautaCadastrada;

public class SessaoStepDefs extends StepDefs {

    @Autowired
    PautaChamadaDireta pautaChamadaDireta;

    @Autowired
    SessaoChamadaDireta sessaoChamadaDireta;

    @Before("@sessao_cadastro")
    public void iniciarContexto() {
        limparBanco();
    }

    private String duracao;

    @Dado("^que exista na base a pauta \"([^\"]*)\"$")
    public void queExistaNaBaseAhPauta(String pauta) throws Exception {
        actions = pautaChamadaDireta.cadastrarPauta(pauta);
        this.pautaDto = obterObjetoRetornado(PautaDto.class);
    }

    @E("^que foi informado a duracao da assenmbléia de \"([^\"]*)\" minutos$")
    public void queFoiInformadoADuracaoDaAssenmbléiaDeMinutos(String duracao) {
        this.duracao = duracao;
    }

    @Quando("^solicitar cadastrar uma nova assembleia$")
    public void solicitarCadastrarUmaNovaAssembleia() throws Exception {
        actions = sessaoChamadaDireta.cadastrarSessao(criarSessao());
    }

    @Então("^Retornar a seguine sessao cadastrada$")
    public void retornarASeguineSessaoCadastrada(List<SessaoDataTable> sessaoDataTables) {
        verificarPautaCadastrada(sessaoDataTables, obterObjetoRetornado(SessaoDto.class));
    }

    private SessaoDto criarSessao() {
        SessaoDto sessaoDto = new SessaoDto();
        sessaoDto.setDuracao(duracao);
        sessaoDto.setPautaId(pautaDto.getId());
        return sessaoDto;
    }
}
