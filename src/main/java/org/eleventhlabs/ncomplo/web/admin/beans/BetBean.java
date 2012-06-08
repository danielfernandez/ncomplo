package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class BetBean implements Serializable {

    private static final long serialVersionUID = 3053553619770813917L;

    
    
    @NotNull
    private Integer id;
    
    @NotNull
    private Integer gameId; 
    
    @NotNull
    private Integer betTypeId; 

    private Integer gameSideAId;

    private Integer gameSideBId;
    
    private Integer scoreA;
    
    private Integer scoreB;
    
    
    
    
    public BetBean() {
        super();
    }




    public Integer getId() {
        return this.id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public Integer getGameId() {
        return this.gameId;
    }


    public void setGameId(final Integer gameId) {
        this.gameId = gameId;
    }


    public Integer getBetTypeId() {
        return this.betTypeId;
    }


    public void setBetTypeId(final Integer betTypeId) {
        this.betTypeId = betTypeId;
    }


    public Integer getGameSideAId() {
        return this.gameSideAId;
    }


    public void setGameSideAId(final Integer gameSideAId) {
        this.gameSideAId = gameSideAId;
    }


    public Integer getGameSideBId() {
        return this.gameSideBId;
    }


    public void setGameSideBId(final Integer gameSideBId) {
        this.gameSideBId = gameSideBId;
    }


    public Integer getScoreA() {
        return this.scoreA;
    }


    public void setScoreA(final Integer scoreA) {
        this.scoreA = scoreA;
    }


    public Integer getScoreB() {
        return this.scoreB;
    }

    
    public void setScoreB(final Integer scoreB) {
        this.scoreB = scoreB;
    }

    
    
}
