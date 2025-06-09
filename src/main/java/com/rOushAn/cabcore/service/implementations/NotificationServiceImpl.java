package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.exceptions.RuntimeConflictException;
import com.rOushAn.cabcore.service.NotificationService;
import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = Logger.getLogger(NotificationServiceImpl.class);
    private final JavaMailSender javaMailSender;

    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private void sendEmailInternal(String[] to, String subject, String body) {
        try {
            if (to == null || to.length == 0 || subject == null || body == null) {
                throw new IllegalArgumentException("To, Subject, and Body must not be null or empty.");
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);
            log.info("Email sent to: " + String.join(", ", to) + " | Subject: " + subject);
        } catch (Exception e) {
            log.error("Failed to send email to: " + Arrays.toString(to), e);
            throw new RuntimeConflictException("Email sending failed: " + e.getMessage());
        }
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        sendEmailInternal(new String[]{to}, subject, body);
    }

    @Override
    public void sendEmail(String[] to, String subject, String body) {
        sendEmailInternal(to, subject, body);
    }
}