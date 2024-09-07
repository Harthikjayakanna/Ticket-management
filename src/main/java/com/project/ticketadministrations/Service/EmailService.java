package com.project.ticketadministrations.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.ticketadministrations.DTO.MailConfig;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    public String sendMail(MailConfig config) {
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setSubject(config.getSubject());
            helper.setTo(config.getTo());
            helper.setText(config.getText());
            sender.send(msg);
            System.out.println("Mail Sended");
            return "mail Sended";
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error occurred while sending mail: " + e.getMessage());
            return "Mail not sended";

        }
    }

}
