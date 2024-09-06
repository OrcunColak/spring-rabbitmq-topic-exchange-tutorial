package com.colak.springtutorial.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerOrdersLogConsumer {

    @RabbitListener(queues = "customer_orders_queue")
    public void process(String message) {
        log.info("Received in customer_orders_queue: {}" , message);
    }
}
