package org.example.notifyservice.service;

import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Locale;

public class EmailService {

    private final JavaMailSender mailSender;
    private final String from;
    private final KafkaTemplate<String, String> kafkaTemplate;
    public EmailService(JavaMailSender mailSender, String from, KafkaTemplate<String, String> kafkaTemplate) {
        this.mailSender = mailSender;
        this.from = from;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEmail(String recipient) {
    }
}
