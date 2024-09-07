package com.project.ticketadministrations.DTO;

import org.springframework.stereotype.Controller;

import lombok.Data;

@Controller
@Data
public class MailConfig {
    private String to, subject, text;

}
