package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Utilisateur;

public interface LoginService {
    public Utilisateur userLogin(String username, String password);
    public String resetPassEmail(String toEmail, String subject, String body);
}
