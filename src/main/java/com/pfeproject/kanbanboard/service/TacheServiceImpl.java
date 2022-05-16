package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Tache;
import com.pfeproject.kanbanboard.repository.SectionRepository;
import com.pfeproject.kanbanboard.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TacheServiceImpl implements TacheService {

    @Autowired
    private final TacheRepository tacheRepository;
    @Autowired
    private final SectionRepository sectionRepository;

    public TacheServiceImpl(TacheRepository tacheRepository, SectionRepository sectionRepository) {
        this.tacheRepository = tacheRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Tache addTache(Tache tache) {
        if (getTaches().stream().anyMatch(e -> Objects.equals(e.getNameTask(), tache.getNameTask()))  ||sectionRepository.findAll().stream().noneMatch(e -> e.getIdSection() == tache.getSection().getIdSection())) {
            return null;
        }
        return tacheRepository.save(tache);
    }

    @Override
    public Tache updateTache(int id, Tache updated) {
        Tache nv = getTache(id);
        nv.setNameTask(updated.getNameTask());
        nv.setDescriptionTask(updated.getDescriptionTask());
        nv.setSection(updated.getSection());
        nv.setColorTask(updated.getColorTask());
        return tacheRepository.save(nv);
    }

    @Override
    public String deleteTache(int id) {
        if (tacheRepository.findAll().stream().anyMatch(e -> e.getIdTask() == id))
        {
            tacheRepository.deleteById(id);
            return "Task deleted successfully.";
        }
        return "Error! Task doesn't exist.";
    }

    @Override
    public Tache getTache(int id) {
        return tacheRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Tache> getTaches() {
        return tacheRepository.findAll();
    }
}
