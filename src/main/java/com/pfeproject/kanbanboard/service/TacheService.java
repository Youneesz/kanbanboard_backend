package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Tache;

import java.util.List;

public interface TacheService {
    public Tache addTache(Tache tache);
    public Tache updateTache(int id, Tache updated);
    public String deleteTache(int id);
    public Tache getTache(int id);
    public List<Tache> getTaches();
}
