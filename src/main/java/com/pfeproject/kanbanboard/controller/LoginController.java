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
    public Boolean resetPassword(@RequestBody Map<String, String> body) {
        if (utilisateurRepository.findAll().stream().anyMatch(e -> e.getEmail().equals(body.get("email")))) {
            loginService.resetPassEmail(body.get("email"), "Réinitialiser votre mot de passe.", "Clicker sur le lien suivant afin de réinitializer votre mot de passe : http://localhost:3000/resetPassword/" + utilisateurRepository.getIdByEmail(body.get("email")));
            return true;
        }
        return false;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the backend";
    }
}
