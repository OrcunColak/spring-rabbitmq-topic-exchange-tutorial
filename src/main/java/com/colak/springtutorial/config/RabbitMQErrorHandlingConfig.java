package com.colak.springtutorial.config;

import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// See https://blog.igventurelli.io/spring-boot-and-rabbitmq-messaging-with-amqp-a96a5dab3d45
@Configuration
public class RabbitMQErrorHandlingConfig {

    // how errors are handled when processing messages, giving more control over what to do if an exception occurs.
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMaxConcurrentConsumers(3);

        // Avoids automatic re-queueing of messages after a failure
        factory.setDefaultRequeueRejected(false);

        // Adds retry capability with up to 3 retry attempts on message processing failures
        factory.setAdviceChain(RetryInterceptorBuilder.stateless().maxAttempts(3).build());
        return factory;
    }
}
