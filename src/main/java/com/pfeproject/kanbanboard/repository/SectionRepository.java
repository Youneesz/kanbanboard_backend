package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SectionRepository extends JpaRepository<Section, Integer>, JpaSpecificationExecutor<Section> {

}