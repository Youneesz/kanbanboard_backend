package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.repository.SessionRepository;
import com.pfeproject.kanbanboard.service.SessionService;
import com.pfeproject.kanbanboard.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sessions")
@CrossOrigin
public class SessionController {
    @Autowired
    private final SessionService SessionService;
    @Autowired
    private final SessionRepository sessionRepository;

    public SessionController(SessionServiceImpl SessionService, SessionRepository sessionRepository) {
        this.SessionService = SessionService;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping(value ="/add")
    public String add(@RequestBody Session session) {
        if (SessionService.addSession(session) == null) {
            return "You already have a session with that name! Please change it to something else.";
        }
        return "Session added successfully!";
    }

    @PutMapping("/update/{id}")
    public Session update(@PathVariable int id, @RequestBody Session nv) {
        return SessionService.updateSession(id, nv);
    }

    @DeleteMapping("/delete/{id}")
    public List<Session> delete(@PathVariable int id) {
        return SessionService.deleteSession(id);
    }

    @GetMapping("/getall")
    public List<Session> getall() {
        return SessionService.getSessions();
    }

    @GetMapping("/get/{id}")
    public Session get(@PathVariable int id) {
        return SessionService.getSession(id);
    }

    @GetMapping("/getall/{id}")
    public List<Session> getOwnerSessions(@PathVariable int id) {return SessionService.getOwnerSessions(id);}

    @GetMapping("/getcount/{id}")
    public int getUsersCount(@PathVariable int id) {
        return SessionService.getCountUsersBySession(id);
    }

    @GetMapping("getsessionusers/{id_session}")
    public List<Map<Integer, String>> getSessionMembersByIdAndUsernames(@PathVariable int id_session) {
        return sessionRepository.getSessionMembersByIdAndUsernames(id_session);
    }
}
