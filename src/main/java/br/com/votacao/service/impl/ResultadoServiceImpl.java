package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.service.KafkaProducer;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.Resultado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.votacao.share.enuns.OpcaoVoto.NAO;
import static br.com.votacao.share.enuns.OpcaoVoto.SIM;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    private final SessaoService sessaoService;
    private final VotoService votoService;
    private final KafkaProducer kafkaProducer;

    public ResultadoServiceImpl(SessaoService sessaoService, VotoService votoService, KafkaProducer kafkaProducer) {
        this.sessaoService = sessaoService;
        this.votoService = votoService;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Resultado resultado(Resultado resultado) {
        Sessao sessao = this.sessaoService.consultar(resultado.getIdPauta(), resultado.getIdSessao());
        List<Voto> votos = this.votoService.consultarVotos(sessao);

        Resultado resultadoFinal = computarResultado(resultado, votos);

        this.kafkaProducer.writeMessage(obterResultadoJson(resultadoFinal));

        return resultadoFinal;
    }

    private String obterResultadoJson(Resultado resultado) {
        ObjectWriter ow = new ObjectMapper().writer();
        String mensagem = null;
        try {
            mensagem = ow.writeValueAsString(resultado);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mensagem;
    }

    private Resultado computarResultado(Resultado resultado, List<Voto> votos) {
        Resultado resultadoFinal = new Resultado();
        resultadoFinal.setIdPauta(resultado.getIdPauta());
        resultadoFinal.setIdSessao(resultado.getIdSessao());
        resultadoFinal.setTotalVotos(votos.size());
        resultadoFinal.setVotoSim(obterVotosPorTipo(votos, SIM.getDescricao()));
        resultadoFinal.setVotosNao(obterVotosPorTipo(votos, NAO.getDescricao()));
        return resultadoFinal;
    }

    private Integer obterVotosPorTipo(List<Voto> votos, String opcaoVoto) {
        List<Voto> votosFiltrados = votos.stream().filter(voto -> opcaoVoto.equals(voto.getVoto())).collect(Collectors.toList());
        return votosFiltrados.size();
    }
}
