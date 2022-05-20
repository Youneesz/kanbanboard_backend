package com.pfeproject.kanbanboard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "section")
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_SECTION", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSection;

    @Column(name = "NAME_SECTION", nullable = false)
    private String nameSection;

    @JsonManagedReference(value = "section-tasks")
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Tache> taches;

    @JsonBackReference(value = "session-section")
    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;

    public Section() {}

    public Section(String nameSection) {
        this.nameSection = nameSection;
    }

    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    public String getNameSection() {
        return nameSection;
    }

    public void setNameSection(String nameSection) {
        this.nameSection = nameSection;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
