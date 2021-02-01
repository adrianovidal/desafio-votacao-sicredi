package br.com.votacao.controller;

import br.com.votacao.service.impl.KafkaProducerImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducerImpl producer;

    public KafkaController(KafkaProducerImpl producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public String sendMessage(@RequestParam("message") String message) {
        this.producer.writeMessage(message);
        return "Published sucessfull";
    }
}
