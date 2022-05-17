package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer>, JpaSpecificationExecutor<Section> {

    @Query(value = "select * from Section where ID_SESSION = ?1 and NAME_SECTION = ?2", nativeQuery = true)
    Section getSectionByNameSectionPerSession(int id_session, String nameSection);

    @Query(value = "select * from Section where ID_SESSION = ?1", nativeQuery = true)
    List<Section> getSectionsBySession(int id_session);


}