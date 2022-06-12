package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.repository.UtilisateurRepository;
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
    @Autowired
    private final UtilisateurRepository utilisateurRepository;


    public LoginController(LoginServiceImpl loginService, UtilisateurRepository utilisateurRepository) {
        this.loginService = loginService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/")
    public Utilisateur login(@RequestBody Map<String ,String> body) {
        return loginService.userLogin(body.get("username"), body.get("password"));
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@RequestBody Map<String, String> body) {
        if (utilisateurRepository.findAll().stream().anyMatch(e -> e.getEmail().equals(body.get("email")))) {
            return loginService.resetPassEmail(body.get("email"), "Resetting your password.", "Click on this link to reset your password : http://localhost:3000/resetPassword/" + utilisateurRepository.getIdByEmail(body.get("email")));
        }
        return "Email doesn't exist in database.";
    }
}
