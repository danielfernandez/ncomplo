package org.eleventhlabs.ncomplo.business.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="MATCH_DATA")
public class Match implements Comparable<Match> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="COMPETITION_ID")
    @NotNull
    private Competition competition; 
    
    @Column(name="NAME")
    @NotNull
    @Length(min=1, max=30)
    private String name;
    
    @Column(name="BET_TYPE")
    @NotNull
    private BetType betType;

    @Column(name="ROUND")
    @NotNull
    private Round round;
    
    @Column(name="DATE")
    @NotNull
    private Calendar date;
    
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

    public void setName(final String name) {
        this.name = name;
    }

    public BetType getBetType() {
        return this.betType;
    }

    public void setBetType(final BetType betType) {
        this.betType = betType;
    }

    public Round getRound() {
        return this.round;
    }

    public void setRound(final Round round) {
        this.round = round;
    }

    public Team getTeamA() {
        return this.teamA;
    }

    public void setTeamA(final Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return this.teamB;
    }

    public void setTeamB(final Team teamB) {
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

    public Calendar getDate() {
        return this.date;
    }

    public void setDate(final Calendar date) {
        this.date = date;
    }

    
    public Competition getCompetition() {
        return this.competition;
    }


    public void setCompetition(final Competition competition) {
        this.competition = competition;
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

    
    
}
