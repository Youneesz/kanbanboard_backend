package com.pfeproject.kanbanboard.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "session")
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_SESSION", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSession;

    @Column(name = "NAME_SESSION", nullable = false)
    private String nameSession;

    @OneToMany(mappedBy = "session")
    private List<Section> sections;

    @ManyToOne
    private Utilisateur owner;

    public Session() {}

    public Session(Integer idSession, String nameSession) {
        this.idSession = idSession;
        this.nameSession = nameSession;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public String getNameSession() {
        return nameSession;
    }

    public void setNameSession(String nameSession) {
        this.nameSession = nameSession;
    }
}
