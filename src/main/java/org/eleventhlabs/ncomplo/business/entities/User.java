package org.eleventhlabs.ncomplo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="USER_DATA")
public class User {

    @Id
    @Column(name="LOGIN")
    @Length(min=3, max=100)
    private String login;
    
    @Column(name="EMAIL")
    @NotNull
    @Length(min=4, max=100)
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name="LEAGUE_ID")
    @NotNull
    private League league;
    
    
    @Column(name="IS_ADMIN")
    @NotNull
    private boolean admin = false;
    
    public User() {
        super();
    }


    public String getLogin() {
        return this.login;
    }


    public void setLogin(final String login) {
        this.login = login;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(final String email) {
        this.email = email;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public League getLeague() {
        return this.league;
    }


    public void setLeague(League league) {
        this.league = league;
    }


    public boolean isAdmin() {
        return this.admin;
    }


    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
}
