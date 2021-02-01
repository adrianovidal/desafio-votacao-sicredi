package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.interceptor.RestTemplateHeaderModifierInterceptor;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.unittest.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static br.com.votacao.share.Constants.ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR;

public class VerificarCpfAssociadoServiceImplTest extends UnitTest {

    protected VerificarCpfAssociadoService verificarCpfAssociadoService;

    RestTemplate restTemplate;

    @Before
    public void inicializarContexto() {
        restTemplate = restTemplate();
        verificarCpfAssociadoService = new VerificarCpfAssociadoServiceImpl(restTemplate);
    }

    @Test
    public void deveriaLancarExcecaoParaAssociadoNaoHabilitadoParaVotacao() {
        contextoExcecao.expect(NegocioException.class);
        contextoExcecao.expectMessage(ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR);

        verificarCpfAssociadoService.verificar("11");
    }

    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory(
                        new SimpleClientHttpRequestFactory()
                )
        );

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateHeaderModifierInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}