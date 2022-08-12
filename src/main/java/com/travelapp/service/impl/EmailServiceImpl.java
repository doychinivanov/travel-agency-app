package com.travelapp.service.impl;

import com.travelapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(TemplateEngine templateEngine, MessageSource messageSource, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.messageSource = messageSource;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String recipient) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("travelagency@gmail.com");
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setSubject(generateEmailSubject());
            mimeMessageHelper.setText(generateMessageContent(recipient), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

    }

    private String generateEmailSubject() {
        return "Welcome to Travel Agency";
    }

    private String generateMessageContent(String recipient) {
        return "Hello, this is a scheduled email. No need to reply.";
    }
}
