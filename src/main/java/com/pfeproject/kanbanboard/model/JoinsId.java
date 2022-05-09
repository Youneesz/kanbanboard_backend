package com.pfeproject.kanbanboard.model;

import java.io.Serializable;
import java.util.Objects;

public class JoinsId implements Serializable {
    private Integer idSession;
    private Integer idUser;

    public JoinsId() {}

    public JoinsId(Integer idSession, Integer idUser) {
        this.idSession = idSession;
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinsId joinsId = (JoinsId) o;
        return Objects.equals(idSession, joinsId.idSession) && Objects.equals(idUser, joinsId.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSession, idUser);
    }
}
