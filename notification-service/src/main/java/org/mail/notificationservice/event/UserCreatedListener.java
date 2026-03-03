package org.mail.notificationservice.event;

import org.mail.notificationservice.service.NotificationService;
import org.mail.notificationservice.service.NotificationServiceImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedListener {

    private final NotificationServiceImpl notificationService;

    public UserCreatedListener(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }


    @KafkaListener(topics="user-created-topic",groupId = "notification-group")
    public void consume(UserCreatedEvent event){

        System.out.println("Received event from Kafka: " + event.getEmail());

        notificationService.sendEmailViaKafka(event);
    }
}
