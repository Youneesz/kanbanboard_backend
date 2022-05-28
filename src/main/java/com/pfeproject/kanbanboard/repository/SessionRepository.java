package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer>, JpaSpecificationExecutor<Session> {
    @Query(value = "select * from Session where ID_USER = ?1", nativeQuery = true)
    List<Session> getOwnerSessions(int ownerid);

    @Query(value = "select * from Session where NAME_SESSION = ?1 and ID_USER = ?2", nativeQuery = true)
    Session getSessionName(String name, int id);

    @Query(value = "select count(*) from SESSION a INNER JOIN JOINS b ON a.id_session = b.id_session where a.id_session = ?1", nativeQuery = true)
    int getCountUsersBySession(int id);

    @Query(value = "select a.id_user, username from Utilisateur a inner join joins b on a.id_user = b.id_user where b.id_session = ?1", nativeQuery = true)
    List<Map<Integer, String>> getSessionMembersByIdAndUsernames(int id_session);
}