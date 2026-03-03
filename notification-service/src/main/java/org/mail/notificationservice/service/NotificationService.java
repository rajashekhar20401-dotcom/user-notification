package org.mail.notificationservice.service;

import org.mail.notificationservice.dto.NotificationRequestDTO;
import org.mail.notificationservice.event.UserCreatedEvent;

public interface NotificationService {

    String sendEmail(NotificationRequestDTO dto);

    String sendEmailViaKafka(UserCreatedEvent dto);
}
