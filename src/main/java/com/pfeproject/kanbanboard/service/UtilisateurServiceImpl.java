package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.model.Tache;
import com.pfeproject.kanbanboard.model.Tag;
import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.repository.SessionRepository;
import com.pfeproject.kanbanboard.repository.TacheRepository;
import com.pfeproject.kanbanboard.repository.TagRepository;
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
    @Autowired
    private final TagRepository tagRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, SessionRepository sessionRepository, TacheRepository tacheRepository, TagRepository tagRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.sessionRepository = sessionRepository;
        this.tacheRepository = tacheRepository;
        this.tagRepository = tagRepository;
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
        //add condition to restrict task assignments to only the session members
        //add condition to make the owner assign tasks
        /*util.getTaches().addAll(updated.getTaches().stream().map(e -> {
            Tache task = tacheRepository.findById(e.getIdTask()).orElseThrow(RuntimeException::new);
            task.getMeantForUsers().add(util);
            return task;
        }).toList());*/
        return utilisateurRepository.save(util);
    }

    @Override
    public String addUserToSession(int id_session, int id_user){
        Utilisateur util = utilisateurRepository.findById(id_user).orElseThrow(RuntimeException::new);
        Session ses = sessionRepository.findById(id_session).orElseThrow(RuntimeException::new);
        if (util.getJoined_sessions().stream().noneMatch(e -> Objects.equals(e.getIdSession(), ses.getIdSession())) && ses.getUsers().stream().noneMatch(e -> e.getIdUser().equals(util.getIdUser())))
        {
            ses.getUsers().add(util);
            util.getJoined_sessions().add(ses);
            utilisateurRepository.save(util);
            //sessionRepository.save(ses);
            return "User " + util.getUsername() + " has been added to " + ses.getNameSession();
        }
        return "Error, user already in session.";
    }

    @Override
    public String removeUserFromSession(int id_session, int id_user) {
        Session ses = sessionRepository.findById(id_session).orElseThrow(RuntimeException::new);
        Utilisateur us = utilisateurRepository.findById(id_user).orElseThrow(RuntimeException::new);
        if (ses.getUsers().stream().noneMatch(e -> e.getIdUser() == id_user)) {
            return "Error, user doesn't belong to the session";
        }
        us.getJoined_sessions().remove(ses);
        ses.getUsers().remove(us);
        sessionRepository.save(ses);
        return "User removed from session successfully";
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
