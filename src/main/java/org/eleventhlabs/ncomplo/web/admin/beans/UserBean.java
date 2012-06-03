package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;

public class UserBean implements Serializable {

    private static final long serialVersionUID = -3792692019298127800L;
    
    
    private String login;
    private String email;
    private Integer leagueId;
    private boolean admin;

    
    public UserBean() {
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


    public Integer getLeagueId() {
        return this.leagueId;
    }


    public void setLeagueId(final Integer leagueId) {
        this.leagueId = leagueId;
    }


    public boolean isAdmin() {
        return this.admin;
    }


    public void setAdmin(final boolean admin) {
        this.admin = admin;
    }

    
    
}
