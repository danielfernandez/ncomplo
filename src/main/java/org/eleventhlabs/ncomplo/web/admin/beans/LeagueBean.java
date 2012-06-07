package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class LeagueBean implements Serializable {
    
    private static final long serialVersionUID = 2981813244600985282L;

    
    @NotNull
    private Integer id;
    
    @NotNull
    private Integer competitionId;
    
    @NotNull
    @Length(min=3,max=200)
    private String name;
    
    @NotNull
    private List<LangBean> namesByLang = new ArrayList<LangBean>();

    @NotNull
    private boolean active = true;

    @NotNull
    private String adminEmail;

    
    @NotNull
    private Map<Integer,Integer> betTypesByGame = new LinkedHashMap<Integer, Integer>();
    
    
    
    public LeagueBean() {
        super();
    }


    

    public Integer getId() {
        return this.id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public Integer getCompetitionId() {
        return this.competitionId;
    }


    public void setCompetitionId(final Integer competitionId) {
        this.competitionId = competitionId;
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


    public String getAdminEmail() {
        return this.adminEmail;
    }


    public void setAdminEmail(final String adminEmail) {
        this.adminEmail = adminEmail;
    }


    public List<LangBean> getNamesByLang() {
        return this.namesByLang;
    }


    public Map<Integer, Integer> getBetTypesByGame() {
        return this.betTypesByGame;
    }


    
    
}
