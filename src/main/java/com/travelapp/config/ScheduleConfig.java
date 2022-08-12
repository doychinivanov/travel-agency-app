package com.travelapp.config;

import com.travelapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    private EmailService emailService;

    @Autowired
    public ScheduleConfig(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 55 9 * * *", zone = "Europe/Sofia")
    public void sendEmailEachDay() {
        this.emailService.sendEmail("doychinivn@gmail.com");
    }
}
