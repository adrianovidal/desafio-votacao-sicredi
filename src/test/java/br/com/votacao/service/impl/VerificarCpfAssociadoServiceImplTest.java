package br.com.votacao.service.impl;

import br.com.votacao.config.UserConfig;
import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.unittest.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static br.com.votacao.share.Constants.ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR;

public class VerificarCpfAssociadoServiceImplTest extends UnitTest {

    protected VerificarCpfAssociadoService verificarCpfAssociadoService;

    RestTemplate restTemplate;
    UserConfig userConfig;

    @Before
    public void inicializarContexto() {
        restTemplate = new RestTemplate();
        userConfig = new UserConfig();
        userConfig.setUrl("https://user-info.herokuapp.com/users/%s");

        verificarCpfAssociadoService = new VerificarCpfAssociadoServiceImpl(restTemplate, userConfig);
    }

    @Test
    public void deveriaLancarExcecaoParaAssociadoNaoHabilitadoParaVotacao() {
        contextoExcecao.expect(NegocioException.class);
        contextoExcecao.expectMessage(ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR);

        verificarCpfAssociadoService.verificar("11");
    }
}