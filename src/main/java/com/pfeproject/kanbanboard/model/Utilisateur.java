package com.pfeproject.kanbanboard.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_USER", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(name = "BIRTHDATE")
    private Date BIRTHDATE;

    @Column(name = "JOINDATE")
    private Date JOINDATE;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Session> sessions;

    public Utilisateur() {}

    public Utilisateur(Date birthdate, Date joindate, String firstName, String lastName, String email, String password) {
        BIRTHDATE = birthdate;
        JOINDATE = joindate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Date getBIRTHDATE() {
        return BIRTHDATE;
    }

    public void setBIRTHDATE(Date BIRTHDATE) {
        this.BIRTHDATE = BIRTHDATE;
    }

    public Date getJOINDATE() {
        return JOINDATE;
    }

    public void setJOINDATE(Date JOINDATE) {
        this.JOINDATE = JOINDATE;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUser=" + idUser +
                ", BIRTHDATE=" + BIRTHDATE +
                ", JOINDATE=" + JOINDATE +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sessions=" + sessions +
                '}';
    }
}
