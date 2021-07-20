package com.example.kafka.producer.producer;

import com.example.kafka.producer.dto.CarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarProducer {

    private final KafkaTemplate<String, CarDTO> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CarProducer.class);
    private final String topic;

    public CarProducer(KafkaTemplate<String, CarDTO> kafkaTemplate, @Value("${topic.name}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(CarDTO carDTO) {
        kafkaTemplate.send(topic, carDTO).addCallback(
                success -> logger.info("Message send " + (success == null ? "" : success.getProducerRecord().value())),
                failure -> logger.info("Message failure" + failure.getMessage())
        );
    }
}
