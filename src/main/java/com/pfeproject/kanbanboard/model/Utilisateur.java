package com.pfeproject.kanbanboard.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable /*, UserDetails*/ {

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

    @Column(name = "bg_color")
    private String bgColor;

    @JsonManagedReference(value = "owned-sessions")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Session> owned_sessions = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Session> joined_sessions = new HashSet<>();


    @ManyToMany(mappedBy = "meantForUsers")
    private Set<Tache> taches = new HashSet<>();

    public Utilisateur() {}

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public String getPfp() {
        return pfp;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public List<Session> getOwned_sessions() {
        return owned_sessions;
    }

    public void setOwned_sessions(List<Session> owned_sessions) {
        this.owned_sessions = owned_sessions;
    }

    public Set<Session> getJoined_sessions() {
        return joined_sessions;
    }

    public void setJoined_sessions(Set<Session> joined_sessions) {
        this.joined_sessions = joined_sessions;
    }

    public Set<Tache> getTaches() {
        return taches;
    }

    public void setTaches(Set<Tache> taches) {
        this.taches = taches;
    }

    public String getBg_color() {
        return bgColor;
    }

    public void setBg_color(String bg_color) {
        this.bgColor = bg_color;
    }

    /*@Override
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
    }*/



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
