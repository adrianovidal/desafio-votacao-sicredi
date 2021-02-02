package br.com.votacao.schedule;

import br.com.votacao.domain.Pauta;
import br.com.votacao.domain.Sessao;
import br.com.votacao.service.KafkaProducer;
import br.com.votacao.service.ResultadoService;
import br.com.votacao.service.SessaoService;
import br.com.votacao.share.response.ResultadoResponse;
import br.com.votacao.share.builders.ResultadoBuild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.votacao.share.util.JsonUtil.toJson;

@Component
@EnableScheduling
public class ResultadoServiceSchedule {

    private static final Logger logger = LoggerFactory.getLogger(ResultadoServiceSchedule.class);

    private final SessaoService sessaoService;
    private final KafkaProducer kafkaProducer;
    private final ResultadoService resultadoService;

    public ResultadoServiceSchedule(SessaoService sessaoService,
                                    KafkaProducer kafkaProducer,
                                    ResultadoService resultadoService) {
        this.sessaoService = sessaoService;
        this.kafkaProducer = kafkaProducer;
        this.resultadoService = resultadoService;
    }

    @Scheduled(cron = "${resultado.repeatInterval}")
    public void veriricarSessosFinalizadasSemResultadoEnviado() {
        List<Sessao> sessoesNaoEnviadas = sessaoService.consultarSessoesFinalizadasSemResultadoEnviaddo();
        sessoesNaoEnviadas.forEach(sessao -> {
            ResultadoResponse resultadoResponse = resultadoService.resultado(criarResultadoParaConsulta(sessao));
            kafkaProducer.writeMessage(toJson(resultadoResponse));
            atualizarSessao(sessao);
            logger.info("*** KafkaProducer enviado...");
        });
        logger.info("*** KafkaProducer finalizado verificação...");
    }

    private void atualizarSessao(Sessao sessao) {
        sessao.setEnviadoKafka(true);
        sessaoService.atualizar(sessao);
    }

    private ResultadoResponse criarResultadoParaConsulta(Sessao sessao) {
        Pauta pauta = sessao.getPauta();
        return ResultadoBuild.of()
                .comIdSessao(sessao.getSequencial())
                .comIdPauta(pauta.getId()).build();
    }
}
