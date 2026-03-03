package org.mail.notificationservice.service;

import org.mail.notificationservice.dto.NotificationRequestDTO;
//import org.mail.notificationservice.entity.Mail;
//import org.mail.notificationservice.repository.MailRepository;
import org.mail.notificationservice.event.UserCreatedEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final JavaMailSender mail;
    //private final MailRepository repo;MailRepository repo)

    public NotificationServiceImpl(JavaMailSender mail)  {
        this.mail = mail;
        //this.repo = repo;
    }
    @Override
    public String sendEmail(NotificationRequestDTO dto) {
        boolean status=false;

        try{

            SimpleMailMessage message=new SimpleMailMessage();
            message.setTo(dto.getEmail());
            message.setSubject("Account Created Successfully");
            message.setText("Hello "+dto.getFirstName()+
                    ",your account has been created successfully");
            mail.send(message);
            status=true;
        }
        catch (Exception e){
            status=false;
        }
//        Mail log=new Mail();
//        log.setName(dto.getFirstName());
//        log.setEmailId(dto.getEmail());
//        log.setEmailSent(status);
//        repo.save(log);
        return status?"Email sent successfully":"Sending Failed";
    }

    public String sendEmailViaKafka(UserCreatedEvent dto) {
        boolean status=false;

        try{

            SimpleMailMessage message=new SimpleMailMessage();
            message.setTo(dto.getEmail());
            message.setSubject("Account Created Successfully");
            message.setText("Hello "+dto.getEmail()+
                    ",your account has been created successfully");
            mail.send(message);
            status=true;
        }
        catch (Exception e){
            status=false;
        }
//        Mail log=new Mail();
//        log.setName(dto.getFirstName());
//        log.setEmailId(dto.getEmail());
//        log.setEmailSent(status);
//        repo.save(log);
        return status?"Email sent successfully":"Sending Failed";
    }
}
