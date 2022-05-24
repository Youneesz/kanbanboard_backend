package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Tache;

import java.util.List;

public interface TacheService {
    public Tache addTache(Tache tache);
    public Tache updateTache(int id, Tache updated);
    public String deleteTache(int id);
    public Tache getTache(int id);
    public List<Tache> getTaches();
    public String addTagToTask(int id_task, int id_tag);
    public String removeTagToTask(int id_task, int id_tag);
    public String assignTask(int id_user, int id_task);
    public String unassignTask(int id_user, int id_task);
}
