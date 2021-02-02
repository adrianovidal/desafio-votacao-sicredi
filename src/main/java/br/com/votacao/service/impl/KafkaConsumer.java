package br.com.votacao.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "my_topic", groupId = "my_group_id")
    public void getMessage(String message) {
        logger.info(String.format("*** Resultado da Votação -> %s", message));
    }
}
