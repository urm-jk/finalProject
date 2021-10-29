package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.services.SendSimpleMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendSimpleMessageImpl implements SendSimpleMessage {
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Uzholdoshev@gamil.com");
        message.setTo(to);
        message.setSubject("Код подтвержден");
        message.setText("Код подтверждения: " + text);
        mailSender.send(message);

    }
}
