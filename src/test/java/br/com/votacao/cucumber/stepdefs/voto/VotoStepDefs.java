package br.com.votacao.cucumber.stepdefs.voto;

import br.com.votacao.cucumber.stepdefs.StepDefs;
import br.com.votacao.cucumber.stepdefs.datatable.VotoDataTable;
import br.com.votacao.share.dto.SessaoDto;
import br.com.votacao.share.dto.VotoDto;
import br.com.votacao.share.response.VotoResponse;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

import java.util.List;

import static br.com.votacao.cucumber.stepdefs.voto.verificador.VotoVerificador.verificarPautaCadastrada;

public class VotoStepDefs extends StepDefs {

    private List<VotoDataTable> votoDataTables;

    @E("^que exista na base a asembleia \"([^\"]*)\";$")
    public void queExistaNaBaseAAsembleia(String idSessao) throws Throwable {
        actions = cadastrarSessao(criarSessao(idSessao));
        sessaoDto = obterObjetoRetornado(SessaoDto.class);
    }

    private SessaoDto criarSessao(String idSessao) {
        SessaoDto sessaoDto = new SessaoDto();
        sessaoDto.setSequencial(Long.parseLong(idSessao));
        sessaoDto.setPautaId(1L);
        sessaoDto.setDuracao("5");
        return sessaoDto;
    }

    @E("^que foi informado o seguinte voto$")
    public void queFoiInformadoOSeguinteVoto(List<VotoDataTable> votoDataTables) {
        this.votoDataTables = votoDataTables;
    }

    private VotoDto criarVoto(VotoDataTable votoDT) {
        VotoDto votoDto = new VotoDto();
        votoDto.setVoto(votoDT.getVoto());
        votoDto.setAssociadoIden(votoDT.getIdAssociado());
        votoDto.setSessaoSequencial(sessaoDto.getSequencial());
        return votoDto;
    }

    @Quando("^solicitar cadastrar um novo voto$")
    public void solicitarCadastrarUmNovoVoto() throws Exception {
        actions = cadastrarVoto(criarVoto(this.votoDataTables.get(0)));
    }

    @Então("^Retornar o seguinte mensasgem \"([^\"]*)\"$")
    public void retornarOSeguinteMensasgem(String mensagem) {
        verificarPautaCadastrada(mensagem, obterObjetoRetornado(VotoResponse.class).getMessage());
    }
}
