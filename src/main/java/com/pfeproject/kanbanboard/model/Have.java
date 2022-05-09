package com.pfeproject.kanbanboard.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "have")
@IdClass(HaveId.class)
public class Have implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_TASK", nullable = false)
    private Integer idTask;

    @Id
    @Column(name = "ID_TAG", nullable = false)
    private Integer idTag;

}
