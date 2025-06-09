package com.rOushAn.cabcore.service;

public interface NotificationService {

    void sendEmail(String to, String subject, String body);

    void sendEmail(String[] to, String subject, String body);
}
