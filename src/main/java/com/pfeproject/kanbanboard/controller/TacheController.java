package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Tache;
import com.pfeproject.kanbanboard.repository.TacheRepository;
import com.pfeproject.kanbanboard.service.TacheService;
import com.pfeproject.kanbanboard.service.TacheServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TacheController {
    private final TacheService tacheService;
    private final TacheRepository tacheRepository;

    public TacheController(TacheServiceImpl tacheService, TacheRepository tacheRepository) {
        this.tacheService = tacheService;
        this.tacheRepository = tacheRepository;
    }

    @PostMapping("/add")
    public Tache add(@RequestBody Tache task) {
        return tacheService.addTache(task);
    }

    @PutMapping("/update/{id}")
    public Tache update(@PathVariable int id, @RequestBody Tache task)
    {
        return tacheService.updateTache(id, task);
    }

    @PostMapping("/addtag/{id_task}/{id_tag}")
    public String addTagToTask(@PathVariable int id_task, @PathVariable int id_tag) {
        return tacheService.addTagToTask(id_task, id_tag);
    }

    @DeleteMapping("/removetag/{id_task}/{id_tag}")
    public String removeTagFromTask(@PathVariable int id_task, @PathVariable int id_tag) {
        return tacheService.removeTagToTask(id_task, id_tag);
    }

    @PostMapping("/addusers/{id_user}/{id_task}")
    public String addUserFromTask(@PathVariable int id_user, @PathVariable int id_task) {
        return tacheService.assignTask(id_user, id_task);
    }

    @DeleteMapping("/removeusers/{id_user}/{id_task}")
    public String removeUserFromTask(@PathVariable int id_user, @PathVariable int id_task) {
        return tacheService.unassignTask(id_user, id_task);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return tacheService.deleteTache(id);
    }

    @GetMapping("/get/{id}")
    public Tache get(@PathVariable int id) {
        return tacheService.getTache(id);
    }

    @GetMapping("/getall")
    public List<Tache> getall() {
        return tacheService.getTaches();
    }

    @GetMapping("/gettaskusers/{id}")
    public List<String> getTaskUsers(@PathVariable int id) {
        return tacheRepository.getTaskUsers(id);
    }
}
