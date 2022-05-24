package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private JavaMailSender mailSender;

    public LoginServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public String userLogin(String username, String password) {
        if (utilisateurRepository.getUserLogin(username, password) == null)  {
            return "Error! Username or password doesn't exist.";
        }
        return "Welcome";
    }

    @Override
    public String resetPassEmail(String toEmail, String subject, String body) {
        if (utilisateurRepository.findAll().stream().anyMatch(e -> e.getEmail().equals(toEmail))) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("kanbanboardreset@gmail.com");
            msg.setTo(toEmail);
            msg.setText(body);
            msg.setSubject(subject);

            mailSender.send(msg);
            return "Check your email messages to reset your password.";
        }
        return "There isn't a user with this email.";
    }
}
