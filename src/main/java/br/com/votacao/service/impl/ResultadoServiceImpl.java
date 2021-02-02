package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.response.ResultadoResponse;
import br.com.votacao.share.builders.ResultadoBuild;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.votacao.share.Constants.SESSAO_NAO_ENCONTRADA;
import static br.com.votacao.share.enuns.OpcaoVoto.NAO;
import static br.com.votacao.share.enuns.OpcaoVoto.SIM;
import static br.com.votacao.share.util.VerificadorUtil.estaNulo;
import static java.time.ZonedDateTime.now;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    private final SessaoService sessaoService;
    private final VotoService votoService;

    public ResultadoServiceImpl(SessaoService sessaoService, VotoService votoService) {
        this.sessaoService = sessaoService;
        this.votoService = votoService;
    }

    @Override
    public ResultadoResponse resultado(ResultadoResponse resultadoResponse) {
        Sessao sessao = this.sessaoService.consultar(resultadoResponse.getIdPauta(), resultadoResponse.getIdSessao());
        validarSessaoAberta(sessao);

        List<Voto> votos = this.votoService.consultarVotos(sessao);

        return computarResultado(resultadoResponse, sessao, votos);
    }

    private void validarSessaoAberta(Sessao sessao) {
        if (estaNulo(sessao)) {
            throw new NegocioException(SESSAO_NAO_ENCONTRADA);
        }
    }

    private ResultadoResponse computarResultado(ResultadoResponse resultadoResponse, Sessao sessao, List<Voto> votos) {
        return ResultadoBuild.of()
                .comTotalVotos(votos.size())
                .comIdPauta(resultadoResponse.getIdPauta())
                .comIdSessao(resultadoResponse.getIdSessao())
                .comResultado(sessao.obterTipoResultado())
                .comVotosSim(obterVotosPorTipo(votos, SIM.getDescricao()))
                .comVotosNao(obterVotosPorTipo(votos, NAO.getDescricao()))
                .build();
    }

    private Integer obterVotosPorTipo(List<Voto> votos, String opcaoVoto) {
        List<Voto> votosFiltrados = votos.stream().filter(voto -> opcaoVoto.equals(voto.getVoto())).collect(Collectors.toList());
        return votosFiltrados.size();
    }
}
