package br.com.votacao.service.impl;

import br.com.votacao.service.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements KafkaProducer {

    private static final String TOPIC = "my_topic";

    public KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void writeMessage(String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
}
