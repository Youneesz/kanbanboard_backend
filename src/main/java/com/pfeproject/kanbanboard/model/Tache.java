package com.pfeproject.kanbanboard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tache")
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_TASK", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTask;

    @Column(name = "NAME_TASK", nullable = false)
    private String nameTask;

    @Column(name = "DESCRIPTION_TASK")
    private String descriptionTask;

    @Column(name = "COLOR_TASK")
    private String colorTask;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "FINISH_DATE")
    private Date finishDate;

    @JsonBackReference(value = "section-tasks")
    @ManyToOne
    @JoinColumn(name = "id_section")
    private Section section;

    @ManyToMany(mappedBy = "tags_taches")
    private List<Tag> taches_tags = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "work_on", joinColumns = {@JoinColumn(name = "ID_TASK")}, inverseJoinColumns = {@JoinColumn(name = "ID_USER")})
    private List<Utilisateur> meantForUsers = new ArrayList<>();

    public Tache() {
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public String getColorTask() {
        return colorTask;
    }

    public void setColorTask(String colorTask) {
        this.colorTask = colorTask;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public List<Tag> getTaches_tags() {
        return taches_tags;
    }

    public void setTaches_tags(List<Tag> taches_tags) {
        this.taches_tags = taches_tags;
    }

    public List<Utilisateur> getMeantForUsers() {
        return meantForUsers;
    }

    public void setMeantForUsers(List<Utilisateur> meantForUsers) {
        this.meantForUsers = meantForUsers;
    }
}
