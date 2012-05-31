package org.eleventhlabs.ncomplo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="BET")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "MATCH_KEY")
    private Match match;
    
    @Column(name = "OWNER_NAME")
    private String ownerName;
    
    @Column(name = "OWNER_ACCOUNT_ID")
    private String ownerAccountId;
    
    @Column(name = "TEAM_A")
    private Team teamA;
    
    @Column(name = "TEAM_B")
    private Team teamB;
    
    @Column(name = "SCORE_A")
    private Integer scoreA;
    
    @Column(name = "SCORE_B")
    private Integer scoreB;
    
    @Column(name = "MATCH_WINNER")
    private MatchWinner matchWinner;


    
    public Bet() {
        super();
    }


    
    public Match getMatch() {
        return this.match;
    }


    public void setMatch(Match match) {
        this.match = match;
    }


    public String getOwnerName() {
        return this.ownerName;
    }


    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    public Team getTeamA() {
        return this.teamA;
    }


    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }
    

    public Team getTeamB() {
        return this.teamB;
    }


    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }


    public Integer getScoreA() {
        return this.scoreA;
    }


    public void setScoreA(Integer scoreA) {
        this.scoreA = scoreA;
    }


    public Integer getScoreB() {
        return this.scoreB;
    }


    public void setScoreB(Integer scoreB) {
        this.scoreB = scoreB;
    }


    public MatchWinner getMatchWinner() {
        return this.matchWinner;
    }


    public void setMatchWinner(MatchWinner matchWinner) {
        this.matchWinner = matchWinner;
    }


    public Integer getId() {
        return this.id;
    }


    public String getOwnerAccountId() {
        return this.ownerAccountId;
    }

    public void setOwnerAccountId(String ownerAccountId) {
        this.ownerAccountId = ownerAccountId;
    }
    
    
    
}
