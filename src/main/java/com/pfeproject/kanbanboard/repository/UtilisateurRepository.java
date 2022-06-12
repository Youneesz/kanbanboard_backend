package com.pfeproject.kanbanboard.repository;

import com.pfeproject.kanbanboard.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>, JpaSpecificationExecutor<Utilisateur> {
    @Query(value = "select * from Utilisateur where USERNAME = :username", nativeQuery = true)
    Utilisateur getUserByUsername(@Param("username") String username);

    @Query(value = "select username from Utilisateur", nativeQuery = true)
    List<Utilisateur> getUsernames();

    @Query(value = "select * from Utilisateur where USERNAME = ?1 and PASSWORD = ?2", nativeQuery = true)
    Utilisateur getUserLogin(String username, String password);

    @Query(value = "select id_user from Utilisateur where email = ?1", nativeQuery = true)
    int getIdByEmail(String email);

}