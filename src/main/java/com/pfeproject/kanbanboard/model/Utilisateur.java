package com.pfeproject.kanbanboard.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_USER", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @Column(name = "JOINDATE")
    private Date joindate;

    @Column(name = "PICTURE")
    private String pfp;

    @ManyToMany(mappedBy = "users")
    private List<Session> session_user;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "work_on", joinColumns = {@JoinColumn(name = "ID_USER")}, inverseJoinColumns = {@JoinColumn(name = "ID_TASK")})
    private List<Tache> taches;

    public Utilisateur() {}

    public Utilisateur(Date birthdate, Date joindate, String firstName, String lastName, String username, String email, String password) {
        this.birthdate = birthdate;
        this.joindate = joindate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
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
        return birthdate;
    }

    public void setBIRTHDATE(Date BIRTHDATE) {
        this.birthdate = BIRTHDATE;
    }

    public Date getJOINDATE() {
        return joindate;
    }

    public void setJOINDATE(Date JOINDATE) {
        this.joindate = JOINDATE;
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

    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }



    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUser=" + idUser +
                ", BIRTHDATE=" + birthdate +
                ", JOINDATE=" + joindate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password +
                '}';
    }
}
