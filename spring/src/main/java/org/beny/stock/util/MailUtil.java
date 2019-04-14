package org.beny.stock.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class MailUtil {

    @Autowired
    private static JavaMailSender mailSender;

    @Autowired
    private static MessageSource messageSource;

    @Value("${registration.mail.url:http://localhost:8081/register/activate?token=}")
    private static String url;

    @Value("${registration.mail.enable:false}")
    private static boolean enabled;

    private MailUtil() {
    }

    public static void sendActivationEmail(String email, String token) {
        if (enabled) {
            String URL = url + token;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(messageSource.getMessage("registration.email.subject", new Object[]{URL}, getLocale()));
            message.setText(messageSource.getMessage("registration.email.activation", new Object[]{URL}, getLocale()));
            mailSender.send(message);
        }
    }

}
