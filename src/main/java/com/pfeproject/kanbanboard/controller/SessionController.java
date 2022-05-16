package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.service.SessionService;
import com.pfeproject.kanbanboard.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@CrossOrigin
public class SessionController {
    @Autowired
    private final SessionService SessionService;

    public SessionController(SessionServiceImpl SessionService) {
        this.SessionService = SessionService;
    }

    @PostMapping("/add")
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
}
