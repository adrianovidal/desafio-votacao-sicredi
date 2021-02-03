package br.com.votacao.cucumber.stepdefs.voto;

import br.com.votacao.controller.errors.Problem;
import br.com.votacao.cucumber.stepdefs.StepDefs;
import br.com.votacao.cucumber.stepdefs.datatable.VotoDataTable;
import br.com.votacao.share.dto.SessaoDto;
import br.com.votacao.share.dto.VotoDto;
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

    @E("^que foi informado os seguintes votos$")
    public void queFoiInformadoOsSeguintesVotos(List<VotoDataTable> votoDataTables) {
        this.votoDataTables = votoDataTables;
    }

    private VotoDto criarVoto(VotoDataTable votoDT) {
        VotoDto votoDto = new VotoDto();
        votoDto.setVoto(votoDT.getVoto());
        votoDto.setAssociadoIden(votoDT.getIdAssociado());
        votoDto.setAssociadoCpf(votoDT.getCpfAssociado());
        votoDto.setSessaoSequencial(sessaoDto.getSequencial());
        return votoDto;
    }

    @Quando("^solicitar cadastrar um novo voto$")
    public void solicitarCadastrarUmNovoVoto() throws Exception {
        actions = cadastrarVoto(criarVoto(this.votoDataTables.get(0)));
    }

    @Então("^Retornar o seguinte mensasgem \"([^\"]*)\"$")
    public void retornarOSeguinteMensasgem(String mensagem) {
        verificarPautaCadastrada(mensagem, obterObjetoRetornado(Problem.class).getMessage());
    }

    @E("^que esista na base os seguintes votos$")
    public void queEsistaNaBaseOsSeguintesVotos(List<VotoDataTable> votoDataTables) {
        votoDataTables.forEach(this::votar);
    }

    private void votar(VotoDataTable votoDataTable) {
        try {
            cadastrarVoto(criarVoto(votoDataTable));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
