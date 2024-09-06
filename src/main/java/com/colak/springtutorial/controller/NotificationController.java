package com.colak.springtutorial.controller;

import com.colak.springtutorial.service.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/rabbitmq")
@RequiredArgsConstructor
public class NotificationController {

    private final OrderProducer orderProducer;

    // http://localhost:8080/api/rabbitmq/sendMessage
    @GetMapping("/sendMessage")
    public ResponseEntity<String> testRabbitmq() {
        orderProducer.publishCustomerOrderLog();

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
