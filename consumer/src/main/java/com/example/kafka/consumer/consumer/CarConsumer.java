package com.example.kafka.consumer.consumer;

import com.example.kafka.consumer.dto.CarDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CarConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CarConsumer.class);

    @Value("${topic.name}")
    private String topic;

    @Value("${spring.kafka.group-id}")
    private String groupId;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "carKafkaListenerContainerFactory")
    public void listenTopicCar(ConsumerRecord<String, CarDTO> record) {
        logger.info("Received message [partition]: " + record.partition());
        logger.info("Received message [value]: " + record.value());
    }


}
