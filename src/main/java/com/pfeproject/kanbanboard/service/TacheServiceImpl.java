package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Tache;
import com.pfeproject.kanbanboard.model.Tag;
import com.pfeproject.kanbanboard.model.Utilisateur;
import com.pfeproject.kanbanboard.repository.SectionRepository;
import com.pfeproject.kanbanboard.repository.TacheRepository;
import com.pfeproject.kanbanboard.repository.TagRepository;
import com.pfeproject.kanbanboard.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheServiceImpl implements TacheService {

    @Autowired
    private final TacheRepository tacheRepository;
    @Autowired
    private final SectionRepository sectionRepository;
    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private final TagRepository tagRepository;

    public TacheServiceImpl(TacheRepository tacheRepository, SectionRepository sectionRepository, UtilisateurRepository utilisateurRepository, TagRepository tagRepository) {
        this.tacheRepository = tacheRepository;
        this.sectionRepository = sectionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Tache addTache(Tache tache) {
        if (tacheRepository.getTachesBySection(tache.getSection().getIdSection()).stream().anyMatch(e -> e.getNameTask().equals(tache.getNameTask()))) {
            return null;
        }
        return tacheRepository.save(tache);
    }

    @Override
    public Tache updateTache(int id, Tache updated) {
        /*if (sectionRepository.findAll().stream().noneMatch(e -> e.getIdSection() == updated.getSection().getIdSection())) {
            return null;
        }*/
        Tache nv = getTache(id);
        nv.setNameTask(updated.getNameTask());
        nv.setDescriptionTask(updated.getDescriptionTask());
        nv.setSection(updated.getSection());
        nv.setColorTask(updated.getColorTask());
        nv.setSection(updated.getSection());
        /*nv.getMeantForUsers().addAll(updated.getMeantForUsers().stream().map(e -> {
            Utilisateur user = utilisateurRepository.findById(e.getIdUser()).orElseThrow(RuntimeException::new);
            user.getTaches().add(nv);
            return user;
        }).toList());*/
        nv.getTaches_tags().addAll(updated.getTaches_tags().stream().map(e -> {
            Tag tag = tagRepository.findById(e.getIdTag()).orElseThrow(RuntimeException::new);
            tag.getTags_taches().add(nv);
            return tag;
        }).toList());
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
