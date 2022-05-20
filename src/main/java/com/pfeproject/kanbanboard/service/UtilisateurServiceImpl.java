package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.model.Tache;
import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.repository.SessionRepository;
import com.pfeproject.kanbanboard.repository.TacheRepository;
import com.pfeproject.kanbanboard.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private final SessionRepository sessionRepository;
    @Autowired
    private final TacheRepository tacheRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, SessionRepository sessionRepository, TacheRepository tacheRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.sessionRepository = sessionRepository;
        this.tacheRepository = tacheRepository;
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
        List<Utilisateur> users = getUsers();
        for (Utilisateur user:users) {
            if (!Objects.equals(user.getIdUser(), id)) {
                if ((updated.getEmail() != null && Objects.equals(user.getEmail().toLowerCase(), updated.getEmail().toLowerCase())) || (updated.getUsername() != null && Objects.equals(user.getUsername().toLowerCase(), updated.getUsername().toLowerCase()))) {
                    return null;
                }
            }
        }
        Utilisateur util = utilisateurRepository.findById(getUser(id).getIdUser()).orElseThrow(RuntimeException::new);
        util.setBirthdate(updated.getBirthdate());
        util.setEmail(updated.getEmail());
        util.setFirstName(updated.getFirstName());
        util.setLastName(updated.getLastName());
        util.setUsername(updated.getUsername());
        util.setPassword(updated.getPassword());
        util.setJoindate(updated.getJoindate());
        util.getJoined_sessions().addAll(updated.getJoined_sessions().stream().map(e -> {
            Session session = sessionRepository.findById(e.getIdSession()).orElseThrow(RuntimeException::new);
            session.getUsers().add(util);
            return session;
        }).toList());
        //add condition to restrict task assignments to only the session members
        //add condition to make the owner assign tasks
        util.getTaches().addAll(updated.getTaches().stream().map(e -> {
            Tache task = tacheRepository.findById(e.getIdTask()).orElseThrow(RuntimeException::new);
            task.getMeantForUsers().add(util);
            return task;
        }).toList());
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
