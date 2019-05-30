package org.beny.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${registration.mail.url:null}")
    private String url;

    @Value("${registration.mail.enable:false}")
    private boolean enabled;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendActivationEmail(String email, String token) {
        if (enabled) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Stock News - Account activation");
            message.setText("To activate you account just click the link below. Too easy!\n" + url + token);
            mailSender.send(message);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }
}
