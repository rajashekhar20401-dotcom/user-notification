package org.mail.notificationservice.controller;

import org.mail.notificationservice.dto.NotificationRequestDTO;
import org.mail.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public String sendNotification(@RequestBody NotificationRequestDTO dto){

        return service.sendEmail(dto);
    }
}
