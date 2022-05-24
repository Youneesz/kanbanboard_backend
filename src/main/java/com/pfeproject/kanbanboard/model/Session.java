package com.pfeproject.kanbanboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name="bg_color")
    private String bgColor;

    @JsonManagedReference(value = "session-section")
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Section> sections = new ArrayList<>();

    @JsonBackReference(value = "owned-sessions")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Utilisateur owner;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "joins", joinColumns = @JoinColumn(name = "id_session"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Set<Utilisateur> users = new HashSet<>();

    public Session() {}

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

    public Set<Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(Set<Utilisateur> users) {
        this.users = users;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Session{" +
                "idSession=" + idSession +
                ", nameSession='" + nameSession + '\'' +
                ", descSession='" + descSession + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", sections=" + sections +
                ", owner=" + owner +
                ", users=" + users +
                '}';
    }
}
