package org.eleventhlabs.ncomplo.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="BET_NEW")
public class BetNew {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Transient
    private MatchNew match;
    
    @Transient
    private String ownerName;
    
    @Transient
    private String ownerAccountId;
    
    @Transient
    private TeamNew teamA;
    
    @Transient
    private TeamNew teamB;
    
    @Transient
    private Integer scoreA;
    
    @Transient
    private Integer scoreB;
    
    @Transient
    private MatchWinner matchWinner;


    
    public BetNew() {
        super();
    }


    
    public MatchNew getMatch() {
        return this.match;
    }


    public void setMatch(final MatchNew match) {
        this.match = match;
    }


    public String getOwnerName() {
        return this.ownerName;
    }


    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }


    public TeamNew getTeamA() {
        return this.teamA;
    }


    public void setTeamA(final TeamNew teamA) {
        this.teamA = teamA;
    }
    

    public TeamNew getTeamB() {
        return this.teamB;
    }


    public void setTeamB(final TeamNew teamB) {
        this.teamB = teamB;
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


    public MatchWinner getMatchWinner() {
        return this.matchWinner;
    }


    public void setMatchWinner(final MatchWinner matchWinner) {
        this.matchWinner = matchWinner;
    }


    public Integer getId() {
        return this.id;
    }


    public String getOwnerAccountId() {
        return this.ownerAccountId;
    }

    public void setOwnerAccountId(final String ownerAccountId) {
        this.ownerAccountId = ownerAccountId;
    }
    
    
    
}
