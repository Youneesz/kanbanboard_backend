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

    @Column(name = "DESCRIPTION_SESSION")
    private String descSession;

    /*@OneToMany(mappedBy = "session")
    private List<Section> sections;*/

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Utilisateur owner;

    @ManyToMany
    @JoinTable(name = "joins", joinColumns = @JoinColumn(name = "id_session"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<Utilisateur> users;

    public Session() {}

    public Session(Integer idSession, String nameSession, String descSession) {
        this.idSession = idSession;
        this.nameSession = nameSession;
        this.descSession = descSession;
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

    public String getDescSession() {
        return descSession;
    }

    public void setDescSession(String descSession) {
        this.descSession = descSession;
    }

    public Utilisateur getOwner() {
        return owner;
    }

    public void setOwner(Utilisateur owner) {
        this.owner = owner;
    }

    public List<Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(List<Utilisateur> users) {
        this.users = users;
    }
}
