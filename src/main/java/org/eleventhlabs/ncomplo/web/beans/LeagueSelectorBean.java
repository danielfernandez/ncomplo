package org.eleventhlabs.ncomplo.web.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class LeagueSelectorBean implements Serializable {

    private static final long serialVersionUID = -4977411862979806096L;
    
    
    @NotNull
    private Integer leagueId;
    
    @NotNull
    private Integer roundId;
    
    
    
    public LeagueSelectorBean() {
        super();
    }


    
    public Integer getLeagueId() {
        return this.leagueId;
    }


    public void setLeagueId(final Integer leagueId) {
        this.leagueId = leagueId;
    }


    public Integer getRoundId() {
        return this.roundId;
    }


    public void setRoundId(final Integer roundId) {
        this.roundId = roundId;
    }
    
    
}
