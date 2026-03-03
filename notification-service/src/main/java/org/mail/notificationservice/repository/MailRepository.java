package org.mail.notificationservice.repository;

import org.mail.notificationservice.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail,Long> {

}
