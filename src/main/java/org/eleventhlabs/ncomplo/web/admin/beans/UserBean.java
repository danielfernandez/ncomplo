package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class UserBean implements Serializable {

    private static final long serialVersionUID = -7694121602535187278L;

    @NotNull
    private String login;
    
    @NotNull
    private String name;
    
    @NotNull
    private String email;

    @NotNull
    private Integer[] leagueIds;
    
    @NotNull
    private boolean admin = false;
    
    @NotNull
    private boolean active = true;

    

    
    public UserBean() {
        super();
    }

    


    public String getLogin() {
        return this.login;
    }


    public void setLogin(final String login) {
        this.login = login;
    }


    public String getName() {
        return this.name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(final String email) {
        this.email = email;
    }


    public boolean isAdmin() {
        return this.admin;
    }


    public void setAdmin(final boolean admin) {
        this.admin = admin;
    }


    public Integer[] getLeagueIds() {
        return this.leagueIds;
    }

    
    public void setLeagueIds(final Integer[] leagueIds) {
        this.leagueIds = leagueIds;
    }


    public boolean isActive() {
        return this.active;
    }


    public void setActive(final boolean active) {
        this.active = active;
    }


    
    
}
