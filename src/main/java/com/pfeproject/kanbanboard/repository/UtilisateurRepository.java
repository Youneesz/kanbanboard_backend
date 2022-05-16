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

}