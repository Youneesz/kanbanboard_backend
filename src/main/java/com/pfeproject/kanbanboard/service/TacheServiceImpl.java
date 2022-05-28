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
        nv.setColorTask(updated.getColorTask());
        nv.setSection(updated.getSection());
        nv.setStartDate(updated.getStartDate());
        nv.setFinishDate(updated.getFinishDate());
        /*nv.getMeantForUsers().addAll(updated.getMeantForUsers().stream().map(e -> {
            Utilisateur user = utilisateurRepository.findById(e.getIdUser()).orElseThrow(RuntimeException::new);
            user.getTaches().add(nv);
            return user;
        }).toList());*/
        /*nv.getTaches_tags().addAll(updated.getTaches_tags().stream().map(e -> {
            Tag tag = tagRepository.findById(e.getIdTag()).orElseThrow(RuntimeException::new);
            tag.getTags_taches().add(nv);
            return tag;
        }).toList());*/
        return tacheRepository.save(nv);
    }

    @Override
    public String assignTask(int id_user, int id_task) {
        Tache task = tacheRepository.findById(id_task).orElseThrow(RuntimeException::new);
        Utilisateur us = utilisateurRepository.findById(id_user).orElseThrow(RuntimeException::new);

        if (task.getMeantForUsers().contains(us) || us.getTaches().contains(task)) {
            return "Error, user already working on that task.";
        }

        task.getMeantForUsers().add(us);
        us.getTaches().add(task);
        tacheRepository.save(task);
        return "User added to task successfully";
    }

    @Override
    public String unassignTask(int id_user, int id_task) {
        Tache task = tacheRepository.findById(id_task).orElseThrow(RuntimeException::new);
        Utilisateur us = utilisateurRepository.findById(id_user).orElseThrow(RuntimeException::new);

        if (task.getMeantForUsers().contains(us) && us.getTaches().contains(task)) {
            task.getMeantForUsers().remove(us);
            us.getTaches().remove(task);
            tacheRepository.save(task);
            return "User removed from task successfully";
        }

        return "Error, user isn't working on that task.";
    }

    @Override
    public String addTagToTask(int id_task, int id_tag) {
        Tache task = tacheRepository.findById(id_task).orElseThrow(RuntimeException::new);
        Tag tag = tagRepository.findById(id_tag).orElseThrow(RuntimeException::new);
        if (task.getTaches_tags().contains(tag) || tag.getTags_taches().contains(task)) {
            return "Error! Task already has that tag.";
        }
        tag.getTags_taches().add(task);
        task.getTaches_tags().add(tag);
        tacheRepository.save(task);
        return "Tag added to task successfully.";
    }

    @Override
    public String removeTagToTask(int id_task, int id_tag) {
        Tache task = tacheRepository.findById(id_task).orElseThrow(RuntimeException::new);
        Tag tag = tagRepository.findById(id_tag).orElseThrow(RuntimeException::new);
        if (task.getTaches_tags().contains(tag) && tag.getTags_taches().contains(task)) {
            tag.getTags_taches().remove(task);
            task.getTaches_tags().remove(tag);
            tacheRepository.save(task);
            return "Tag removed";
        }
        return "Error, task doesn't have that tag";
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
