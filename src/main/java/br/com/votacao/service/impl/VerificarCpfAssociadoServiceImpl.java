package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.share.StatusCpf;
import br.com.votacao.share.builders.StatusCpfBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static br.com.votacao.share.Constants.ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR;
import static br.com.votacao.share.enuns.StatusPermissao.UNABLE_TO_VOTE;
import static br.com.votacao.share.util.VerificadorUtil.estaNuloOuVazio;
import static java.text.MessageFormat.format;

@Service
public class VerificarCpfAssociadoServiceImpl implements VerificarCpfAssociadoService {

    public static final String URI_VERIFICADOR_CPF = "https://user-info.herokuapp.com/users/{0}";

    private final RestTemplate restTemplate;

    @Autowired
    public VerificarCpfAssociadoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void verificar(String cpf) {
        if (estaNuloOuVazio(cpf)) { return; }

        StatusCpf statusCpfResult = consultarServicoValidacaoCpf(cpf);

        if (UNABLE_TO_VOTE.equals(statusCpfResult.getStatus())) {
            throw new NegocioException(ASSOCIADO_IMPOSSIBILITADO_DE_VOTAR);
        }
    }

    private StatusCpf consultarServicoValidacaoCpf(String cpf) {
        final String uri = format(URI_VERIFICADOR_CPF, cpf);

        StatusCpf statusCpfResult;

        try {
            statusCpfResult = this.restTemplate.getForObject(uri, StatusCpf.class);
        } catch (HttpClientErrorException e) {
            statusCpfResult = StatusCpfBuild.associadoIncapazDeVoto();
        }
        return statusCpfResult;
    }
}
