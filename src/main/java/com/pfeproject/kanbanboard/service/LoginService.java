package com.pfeproject.kanbanboard.service;

public interface LoginService {
    public String userLogin(String username, String password);
    public String resetPassEmail(String toEmail, String subject, String body);
}
