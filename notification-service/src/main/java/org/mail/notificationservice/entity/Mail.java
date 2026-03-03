package org.mail.notificationservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="email_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emailId;

    private boolean emailSent;
}
