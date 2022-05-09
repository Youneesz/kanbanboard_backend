package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TacheRepository extends JpaRepository<Tache, Integer>, JpaSpecificationExecutor<Tache> {

}