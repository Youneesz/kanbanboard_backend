package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Session;

import java.util.List;

public interface SessionService {
    public Session addSession(Session session);
    public Session updateSession(int id, Session updated);
    public List<Session>  deleteSession(int id);
    public Session getSession(int id);
    public List<Session> getSessions();
    public List<Session> getOwnerSessions(int id);
}
