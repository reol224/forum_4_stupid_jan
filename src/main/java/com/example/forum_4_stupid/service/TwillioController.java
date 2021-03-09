package com.example.forum_4_stupid.service;

import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@RequestMapping("/api/email")
public class TwillioController {
    @Value("${spring.sendgrid.api-key}")
    String SENDGRID_KEY;

    @PostMapping("/send")
    public void sendEmail(@RequestParam String from, @RequestParam String subject, @RequestParam String to, @RequestParam String body){
        Email fromWho = new Email(from);
        Email toWho = new Email(to);
        Content content = new Content("text/plain",body);
        Mail mail = new Mail(fromWho, subject, toWho, content);
        SendGrid sendGrid = new SendGrid(SENDGRID_KEY);
        Request request = new Request();
        try{
            request.setBody(mail.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
