package com.pfeproject.kanbanboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tache")
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_TASK", nullable = false)
    private Integer idTask;

    @Column(name = "ID_SECTION", nullable = false)
    private Integer idSection;

    @Column(name = "NAME_TASK", nullable = false)
    private String nameTask;

    @Column(name = "DESCRIPTION_TASK")
    private String descriptionTask;

    @Column(name = "COLOR_TASK")
    private String colorTask;

}
