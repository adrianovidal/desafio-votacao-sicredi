package br.com.votacao.service;

public interface KafkaProducer {

    void writeMessage(String message);
}
