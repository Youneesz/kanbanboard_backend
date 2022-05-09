package com.pfeproject.kanbanboard.model;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
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
}
