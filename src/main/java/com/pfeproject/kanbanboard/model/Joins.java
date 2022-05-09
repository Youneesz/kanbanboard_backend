package com.pfeproject.kanbanboard.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "joins")
@IdClass(JoinsId.class)
public class Joins implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_SESSION", nullable = false)
    private Integer idSession;

    @Id
    @Column(name = "ID_USER", nullable = false)
    private Integer idUser;

}
