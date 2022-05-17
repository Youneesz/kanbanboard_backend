package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Integer>, JpaSpecificationExecutor<Tache> {

    @Query(value = "select * from Tache where ID_SECTION=?1", nativeQuery = true)
    List<Tache> getTachesBySection(int section_id);

    @Query(value = "select * from Tache where ID_SECTION=?1 and NAME_TASK=?2", nativeQuery = true)
    Tache getTacheBySectionAAndNameTask(int section_id, String name);
}