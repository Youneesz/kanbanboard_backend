package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Joins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JoinsRepository extends JpaRepository<Joins, Integer>, JpaSpecificationExecutor<Joins> {

}