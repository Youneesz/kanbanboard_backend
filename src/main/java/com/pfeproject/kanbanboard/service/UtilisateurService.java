package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    public Utilisateur addUser(Utilisateur user);
    public Utilisateur updateUser(int id, Utilisateur updated);
    public int deleteUser(int id);
    public Utilisateur getUser(int id);
    public List<Utilisateur> getUsers();
}
