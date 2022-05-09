package com.pfeproject.kanbanboard.model;

import javax.persistence.*;
import java.io.Serializable;

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

    public Tag() {}

    public Tag(String nameTag) {
        this.nameTag = nameTag;
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

    @Override
    public String toString() {
        return "Tag{" +
                "idTag=" + idTag +
                ", nameTag='" + nameTag + '\'' +
                '}';
    }
}
