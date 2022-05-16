package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.service.UtilisateurService;
import com.pfeproject.kanbanboard.service.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UtilisateurController {
    @Autowired
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurServiceImpl utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/add")
    public String add(@RequestBody Utilisateur user) {
        if (utilisateurService.addUser(user) == null) {
            return "Error, there is already a user using either same email or username.";
        }
        return "User added successfully.";
    }

    @PutMapping("/update/{id}")
    public Utilisateur update(@PathVariable int id, @RequestBody Utilisateur nv) {
        return utilisateurService.updateUser(id, nv);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        if (utilisateurService.deleteUser(id) == 0) {
            return "Error! User doesn't exist.";
        }
        return "User " + id + " has been deleted!";
    }

    @GetMapping("/getall")
    public List<Utilisateur> getall() {
        return utilisateurService.getUsers();
    }

    @GetMapping("/get/{id}")
    public Utilisateur get(@PathVariable int id) {
        return utilisateurService.getUser(id);
    }
}
