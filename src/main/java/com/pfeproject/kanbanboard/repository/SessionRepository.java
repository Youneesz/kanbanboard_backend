package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SessionRepository extends JpaRepository<Session, Integer>, JpaSpecificationExecutor<Session> {

}