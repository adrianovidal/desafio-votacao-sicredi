package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.service.validator.ValidadorSessao;
import br.com.votacao.share.builders.ResultadoBuild;
import br.com.votacao.share.response.Resultado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.votacao.share.enuns.OpcaoVoto.NAO;
import static br.com.votacao.share.enuns.OpcaoVoto.SIM;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    private final SessaoService sessaoService;
    private final VotoService votoService;
    private final ValidadorSessao validadorSessao;

    public ResultadoServiceImpl(SessaoService sessaoService,
                                VotoService votoService,
                                ValidadorSessao validadorSessao) {
        this.sessaoService = sessaoService;
        this.votoService = votoService;
        this.validadorSessao = validadorSessao;
    }

    @Override
    public Resultado resultado(Resultado resultado) {
        Sessao sessao = sessaoService.consultar(resultado.getIdPauta(), resultado.getIdSessao());
        validadorSessao.validar(sessao);

        List<Voto> votos = votoService.consultarVotos(sessao);

        return computarResultado(resultado, sessao, votos);
    }

    private Resultado computarResultado(Resultado resultado, Sessao sessao, List<Voto> votos) {
        return ResultadoBuild.of()
                .comTotalVotos(votos.size())
                .comIdPauta(resultado.getIdPauta())
                .comIdSessao(resultado.getIdSessao())
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
