package org.eleventhlabs.ncomplo.business.entities;

import java.util.Calendar;


public class MatchNew implements Comparable<MatchNew> {

    private Integer id;
    
    private Competition competition; 
    
    private String name;
    
    private BetType betType;

    private RoundNew round;
    
    private Calendar date;
    
    private TeamNew teamA;
    
    private TeamNew teamB;
    
    private Integer scoreA;
    
    private Integer scoreB;


    
    public MatchNew() {
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

    public RoundNew getRound() {
        return this.round;
    }

    public void setRound(final RoundNew round) {
        this.round = round;
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
    public int compareTo(final MatchNew o) {
        final int dateComp = this.date.compareTo(o.date);
        if (dateComp == 0) {
            return this.name.compareTo(o.name);
        }
        return dateComp;
    }

    
    
}
