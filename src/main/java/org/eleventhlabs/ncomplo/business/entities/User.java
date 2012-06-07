package org.eleventhlabs.ncomplo.business.entities;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="USER_DATA")
public class User implements Comparable<User> {

    @Id
    @Column(name="LOGIN",length=100)
    private String login;
    
    
    @Column(name="NAME",nullable=false,length=600)
    private String name;
    
    
    @Column(name="EMAIL",nullable=false,length=200)
    private String email;

    
    @Column(name="PASSWORD",nullable=true,length=500)
    private String password;

    
    @ManyToMany(cascade=CascadeType.ALL,mappedBy="participants")
    private Set<League> leagues = new LinkedHashSet<League>();
    
    
    @Column(name="IS_ADMIN",nullable=false)
    private boolean admin = false;
    
    
    @Column(name="ACTIVE",nullable=false)
    private boolean active = true;
    
    
    
    
    
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


    public void setPassword(final String password) {
        this.password = password;
    }


    public boolean isAdmin() {
        return this.admin;
    }


    public void setAdmin(final boolean admin) {
        this.admin = admin;
    }


    public Set<League> getLeagues() {
        return this.leagues;
    }


    public String getName() {
        return this.name;
    }

    
    public void setName(final String name) {
        this.name = name;
    }


    public boolean isActive() {
        return this.active;
    }


    public void setActive(final boolean active) {
        this.active = active;
    }



    @Override
    public int compareTo(final User o) {
        return this.getName().compareTo(o.getName());
    }
    
    

}
