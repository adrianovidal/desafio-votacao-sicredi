package br.com.votacao.service.impl;

import br.com.votacao.config.UserConfig;
import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.share.StatusCpf;
import br.com.votacao.share.builders.StatusCpfBuild;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static br.com.votacao.share.Constants.ASSOCIADO_CPF_INVALIDO;
import static br.com.votacao.share.Constants.ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR;
import static br.com.votacao.share.builders.StatusCpfBuild.associadoComCpfInvalido;
import static br.com.votacao.share.enuns.StatusPermissao.UNABLE_TO_VOTE;
import static java.lang.String.format;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VerificarCpfAssociadoServiceImplTest {

    protected VerificarCpfAssociadoService verificarCpfAssociadoService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock RestTemplate restTemplateMock;
    UserConfig userConfig;

    private String cpf;

    @Before
    public void inicializarContexto() {
        userConfig = new UserConfig();
        userConfig.setUrl("https://user-info.herokuapp.com/users/%s");
        cpf = "61048608000";
        verificarCpfAssociadoService = new VerificarCpfAssociadoServiceImpl(restTemplateMock, userConfig);
    }

    @Test
    public void aoVerificarDadoQueCpfEstejaValidoDeveriaNaoGerarExcecao() {
        StatusCpf statusCpf = new StatusCpf();
        when(restTemplateMock.getForObject(gerarUri(), StatusCpf.class)).thenReturn(statusCpf);

        verificarCpfAssociadoService.verificar(cpf);
    }

    @Test
    public void aoVerificarDadoQueCpfImposibilitadoDeVotarDeveriaLancarExecaoComAhMensagemEsperada() {
        StatusCpf statusCpfImposibilitadoDeVotar = StatusCpfBuild.of().comStatus(UNABLE_TO_VOTE).build();
        when(restTemplateMock.getForObject(gerarUri(), StatusCpf.class)).thenReturn(statusCpfImposibilitadoDeVotar);

        exception.expect(NegocioException.class);
        exception.expectMessage(ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR);

        verificarCpfAssociadoService.verificar(cpf);
    }

    @Test
    public void aoVerificarDadoQueCpfEstejaInvalidoDeveriaLancarExecaoComAhMensagemEsperada() {
        when(restTemplateMock.getForObject(gerarUri(), StatusCpf.class)).thenReturn(associadoComCpfInvalido());

        exception.expect(NegocioException.class);
        exception.expectMessage(ASSOCIADO_CPF_INVALIDO);

        verificarCpfAssociadoService.verificar(cpf);
    }

    @Test
    public void aoVerificarDadoQueServicoEstavaSemAcessoDeveriaLancarExecaoComAhMensagemEsperada() {
        when(restTemplateMock.getForObject(gerarUri(), StatusCpf.class)).thenThrow(new HttpClientErrorException(HttpStatus.NO_CONTENT));

        exception.expect(NegocioException.class);
        exception.expectMessage(ASSOCIADO_CPF_INVALIDO);

        verificarCpfAssociadoService.verificar(cpf);
    }

    private String gerarUri() {
        return format(this.userConfig.getUrl(), cpf);
    }
}
