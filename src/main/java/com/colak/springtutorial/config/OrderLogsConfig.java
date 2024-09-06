package com.colak.springtutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderLogsConfig {

    public static final String ORDER_LOGS_EXCHANGE = "order-logs-exchange";

    // Define a TopicExchange named "order-logs-exchange"
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(ORDER_LOGS_EXCHANGE);
    }

    // Define a Queue named "customer_orders_queue"
    @Bean
    public Queue customerOrdersQueue() {
        return new Queue("customer_orders_queue");
    }

    // Define a Queue named "all_order_logs_queue"
    @Bean
    public Queue allOrderLogsQueue() {
        return new Queue("all_orders_logs_queue");
    }

    // Define a Queue named "electronics_order_logs_queue"
    @Bean
    public Queue electronicsOrderLogsQueue() {
        return new Queue("electronics_orders_logs_queue");
    }

    // Create a binding between "customer_orders_queue" and the exchange with the routing key pattern "order.logs.customer.#"
    @Bean
    public Binding customerOrdersBinding(TopicExchange topicExchange, Queue customerOrdersQueue) {
        return BindingBuilder.bind(customerOrdersQueue)
                .to(topicExchange)
                .with("order.logs.customer.#");
    }

    // Create a binding between "all_order_logs_queue" and the exchange with the routing key pattern "order.logs.#"
    @Bean
    public Binding allOrdersBinding(TopicExchange topicExchange, Queue allOrderLogsQueue) {
        return BindingBuilder.bind(allOrderLogsQueue)
                .to(topicExchange)
                .with("order.logs.#");
    }

    // Create a binding between "electronics_order_logs_queue" and the exchange with the routing key pattern "order.logs.*.electronics"
    @Bean
    public Binding electronicsOrdersBinding(TopicExchange topicExchange, Queue electronicsOrderLogsQueue) {
        return BindingBuilder.bind(electronicsOrderLogsQueue)
                .to(topicExchange)
                .with("order.logs.*.electronics");
    }
}
