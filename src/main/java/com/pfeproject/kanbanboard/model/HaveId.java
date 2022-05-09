package com.pfeproject.kanbanboard.model;

import java.io.Serializable;
import java.util.Objects;

public class HaveId implements Serializable {
    private Integer idTask;
    private Integer idTag;

    public HaveId() {}

    public HaveId(Integer idTask, Integer idTag) {
        this.idTask = idTask;
        this.idTag = idTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HaveId haveId = (HaveId) o;
        return Objects.equals(idTask, haveId.idTask) && Objects.equals(idTag, haveId.idTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, idTag);
    }
}
