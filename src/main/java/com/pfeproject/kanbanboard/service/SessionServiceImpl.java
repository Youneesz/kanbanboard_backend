package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.repository.SessionRepository;
import com.pfeproject.kanbanboard.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SessionServiceImpl implements SessionService{
    @Autowired
    private final SessionRepository sessionRepository;
    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, UtilisateurRepository utilisateurRepository) {
        this.sessionRepository = sessionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Session addSession(Session session) {
        if (sessionRepository.getSessionName(session.getNameSession(), session.getOwner().getIdUser()) != null)  {
            return null;
        }
        return sessionRepository.save(session);
    }

    @Override
    public Session updateSession(int id, Session updated) {
        List<Session> userSessions = sessionRepository.getOwnerSessions(updated.getOwner().getIdUser());
        for (Session session:userSessions) {
            if (session.getIdSession() != id) {
                if (Objects.equals(session.getNameSession(), updated.getNameSession())) {
                    return null;
                }
            }
        }
        Session nv = getSession(id);
        nv.setNameSession(updated.getNameSession());
        nv.setDescSession(updated.getDescSession());
        nv.setOwner(updated.getOwner());
        return sessionRepository.save(nv);
    }

    @Override
    public List<Session> deleteSession(int id) {
       sessionRepository.deleteById(id);
       return getSessions();
    }

    @Override
    public Session getSession(int id) {
        return sessionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public List<Session> getOwnerSessions(int id) {
        return sessionRepository.getOwnerSessions(id);
    }

    @Override
    public int getCountUsersBySession(int id) {
        return sessionRepository.getCountUsersBySession(id);
    }
}
