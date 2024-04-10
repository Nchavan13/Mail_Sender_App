package com.niltech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.niltech.model.EmailForm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("emailForm", new EmailForm());
        return "mailForm";
    }

    @PostMapping("/sendMail")
    public String sendMail(@ModelAttribute("emailForm") EmailForm emailForm, Model model) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailForm.getTo());
        message.setSubject(emailForm.getSubject());
        message.setText(emailForm.getBody());
        emailSender.send(message);
        model.addAttribute("message", "Mail sent successfully!");
        return "result";
    }
}

