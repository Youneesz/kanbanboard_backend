package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Tache;
import com.pfeproject.kanbanboard.service.TacheService;
import com.pfeproject.kanbanboard.service.TacheServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TacheController {
    private final TacheService tacheService;

    public TacheController(TacheServiceImpl tacheService) {
        this.tacheService = tacheService;
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
}
