package com.colak.springtutorial.service.producer;

import com.colak.springtutorial.config.OrderLogsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final AmqpTemplate amqpTemplate;

    public void publishCustomerOrderLog() {
        String routingKey = "order.logs.customer.electronics";
        String message = "Customer order log for electronics";
        amqpTemplate.convertAndSend(OrderLogsConfig.ORDER_LOGS_EXCHANGE, routingKey, message);
    }

    // Add similar methods to publish messages with other routing keys
}
