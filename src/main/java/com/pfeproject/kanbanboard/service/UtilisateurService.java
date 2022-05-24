package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.model.Tache;
import com.pfeproject.kanbanboard.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    public Utilisateur addUser(Utilisateur user);
    public Utilisateur updateUser(int id, Utilisateur updated);
    public int deleteUser(int id);
    public Utilisateur getUser(int id);
    public List<Utilisateur> getUsers();
    public String removeUserFromSession(int id_session, int id_user);
    public String addUserToSession(int id_session, int id_user);
}
