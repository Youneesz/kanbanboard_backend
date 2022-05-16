package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur addUser(Utilisateur user) {
        if (getUsers().stream().noneMatch(e -> e.getEmail().equals(user.getEmail())) && getUsers().stream().noneMatch(e -> e.getUsername().equals(user.getUsername()))) {
            return utilisateurRepository.save(user);
        }
        return null;
    }

    @Override
    public Utilisateur updateUser(int id, Utilisateur updated) {
        Utilisateur util = utilisateurRepository.findById(getUser(id).getIdUser()).orElseThrow(RuntimeException::new);
        util.setBIRTHDATE(updated.getBIRTHDATE());
        util.setEmail(updated.getEmail());
        util.setFirstName(updated.getFirstName());
        util.setLastName(updated.getLastName());
        util.setPassword(updated.getPassword());
        util.setJOINDATE(updated.getJOINDATE());
        return utilisateurRepository.save(util);
    }

    @Override
    public int deleteUser(int id) {
        if (getUsers().stream().anyMatch(e -> e.getIdUser() == id)) {
            utilisateurRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Utilisateur getUser(int id) {
        return utilisateurRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Utilisateur> getUsers() {
        return utilisateurRepository.findAll();
    }
}
