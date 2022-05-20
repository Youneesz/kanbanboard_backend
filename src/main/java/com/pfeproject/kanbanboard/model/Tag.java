package com.pfeproject.kanbanboard.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_TAG", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTag;

    @Column(name = "NAME_TAG", nullable = false)
    private String nameTag;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "have", joinColumns = @JoinColumn(name = "ID_TAG"), inverseJoinColumns = @JoinColumn(name = "ID_TASK"))
    private List<Tache> tags_taches = new ArrayList<>();

    public Tag() {}

    public List<Tache> getTags_taches() {
        return tags_taches;
    }

    public void setTags_taches(List<Tache> taches) {
        this.tags_taches = taches;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }
}
