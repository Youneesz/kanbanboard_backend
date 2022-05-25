package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.service.LoginService;
import com.pfeproject.kanbanboard.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController
{
    @Autowired
    private final LoginService loginService;

    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public Utilisateur login(@RequestBody Map<String ,String> body) {
        return loginService.userLogin(body.get("username"), body.get("password"));
    }

    @GetMapping("/resetpassword")
    public String resetPassword(@RequestBody Map<String ,String> body) {
        return loginService.resetPassEmail(body.get("email"), "Password reset", "testing password reset");
    }
}
