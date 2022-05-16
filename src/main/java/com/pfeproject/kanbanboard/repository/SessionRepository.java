package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer>, JpaSpecificationExecutor<Session> {
    @Query(value = "select * from Session where ID_USER = ?1", nativeQuery = true)
    List<Session> getOwnerSessions(int ownerid);
}