package org.eleventhlabs.ncomplo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="MATCH_DATA")
public class Match implements Comparable<Match> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="BET_TYPE")
    private BetType betType;

    @Column(name="ROUND")
    private Round round;
    
    @Column(name="DATE")
    private String date;
    
    @Column(name="TEAM_A")
    private Team teamA;
    
    @Column(name="TEAM_B")
    private Team teamB;
    
    @Column(name="SCORE_A")
    private Integer scoreA;
    
    @Column(name="SCORE_B")
    private Integer scoreB;


    
    public Match() {
        super();
    }
    
    
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BetType getBetType() {
        return this.betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public Round getRound() {
        return this.round;
    }

    public void setRound(Round round) {
        this.round = round;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public boolean isTeamsDefined() {
        return (this.teamA != null && this.teamB != null);
    }
    
    public boolean isScoreDefined() {
        return (this.scoreA != null && this.scoreB != null);
    }
    
    
    
    @Override
    public int compareTo(final Match o) {
        final int dateComp = this.date.compareTo(o.date);
        if (dateComp == 0) {
            return this.name.compareTo(o.name);
        }
        return dateComp;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    
}
