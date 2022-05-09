package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Have;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HaveRepository extends JpaRepository<Have, Integer>, JpaSpecificationExecutor<Have> {

}