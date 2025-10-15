package br.rafael.users.api.services.impl;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.rafael.users.api.services.BrokerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitMQServiceImpl implements BrokerService {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String type, String exchange, Object data) {
        final String routingKey = type + ".#";
        try {
            final String json = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(exchange, routingKey, json, message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing message " + e.getMessage());
        }
    }
    
}
