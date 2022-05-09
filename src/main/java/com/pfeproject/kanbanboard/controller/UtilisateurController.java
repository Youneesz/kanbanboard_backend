package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/add")
    public Utilisateur add(@RequestBody Utilisateur user) {
        return utilisateurRepository.save(user);
    }
}
