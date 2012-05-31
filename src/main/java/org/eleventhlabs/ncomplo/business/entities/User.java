package org.eleventhlabs.ncomplo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_DATA")
public class User {

    @Id
    @Column(name="LOGIN")
    private String login;
    
    @Column(name="EMAIL")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    
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


    public void setPasswrod(String password) {
        this.password = password;
    }
    
    
    
}
