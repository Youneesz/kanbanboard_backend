package com.pfeproject.kanbanboard.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
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

    @ManyToOne
    @JoinColumn(name = "id_section")
    private Section section;

    @ManyToMany
    @JoinTable(name = "have", joinColumns = @JoinColumn(name = "ID_TASK"), inverseJoinColumns = @JoinColumn(name = "ID_TAG"))
    private List<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "taches")
    private List<Utilisateur> users;

    public Tache(String nameTask, String descriptionTask, String colorTask, List<Tag> tags) {
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.colorTask = colorTask;
        this.tags = tags;
    }

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
}
