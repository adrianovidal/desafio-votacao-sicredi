package br.com.votacao.service.impl;

import br.com.votacao.config.UserConfig;
import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.share.StatusCpf;
import br.com.votacao.share.builders.StatusCpfBuild;
import br.com.votacao.share.enuns.StatusPermissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static br.com.votacao.share.enuns.StatusPermissao.mapStatusPermissao;
import static br.com.votacao.share.util.VerificadorUtil.estaNuloOuVazio;
import static java.lang.String.format;

@Service
public class VerificarCpfAssociadoServiceImpl implements VerificarCpfAssociadoService {

    private final RestTemplate restTemplate;
    private final UserConfig userConfig;

    @Autowired
    public VerificarCpfAssociadoServiceImpl(RestTemplate restTemplate, UserConfig userConfig) {
        this.restTemplate = restTemplate;
        this.userConfig = userConfig;
    }

    @Override
    public void verificar(String cpf) {
        if (estaNuloOuVazio(cpf)) { return; }

        StatusCpf statusCpfResult = consultarServicoValidacaoCpf(cpf);
        StatusPermissao status = statusCpfResult.getStatus();

        if (mapStatusPermissao.containsKey(status)) {
            throw new NegocioException(mapStatusPermissao.get(status));
        }
    }

    private StatusCpf consultarServicoValidacaoCpf(String cpf) {
        final String uri = format(this.userConfig.getUrl(), cpf);

        StatusCpf statusCpfResult;

        try {
            statusCpfResult = this.restTemplate.getForObject(uri, StatusCpf.class);
        } catch (HttpClientErrorException e) {
            statusCpfResult = StatusCpfBuild.associadoComCpfInvalido();
        }
        return statusCpfResult;
    }
}
