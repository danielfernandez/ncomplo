package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;


public class ParticipationBean implements Serializable {

    private static final long serialVersionUID = 2611311055987957262L;


    @NotNull
    private String login;
    
    @NotNull
    private Integer leagueId;
    
    @NotNull
    private Map<Integer,BetBean> betsByGame = new LinkedHashMap<Integer, BetBean>();
    
    
    
    
    public ParticipationBean() {
        super();
    }



    public Map<Integer, BetBean> getBetsByGame() {
        return this.betsByGame;
    }



    public void setBetsByGame(final Map<Integer, BetBean> betsByGame) {
        this.betsByGame = betsByGame;
    }



    public String getLogin() {
        return this.login;
    }



    public Integer getLeagueId() {
        return this.leagueId;
    }



    public void setLogin(final String login) {
        this.login = login;
    }



    public void setLeagueId(final Integer leagueId) {
        this.leagueId = leagueId;
    }

    
    
}
