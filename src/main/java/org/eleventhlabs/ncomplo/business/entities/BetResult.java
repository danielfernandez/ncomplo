package org.eleventhlabs.ncomplo.business.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

public class BetResult implements Serializable {

    private static final long serialVersionUID = -688318849853967583L;
    
    private final RoundNew round;
    private final Calendar date;
    private final String name;
    private final TeamNew teamA;
    private final TeamNew teamB;
    private final String score;
    private final String realScore;
    private final MatchWinner matchWinner;
    private final MatchWinner realMatchWinner;
    private final Boolean closed;
    private final LinkedHashSet<BetFragment> betFragments;
    private final LinkedHashSet<BetFragment> betWins;
    private final LinkedHashSet<BetFragment> betLoses;
    private final Integer points;


    public BetResult(final RoundNew round, final Calendar date, final String name, 
            final TeamNew teamA, final TeamNew teamB, final String score, final String realScore, 
            final MatchWinner matchWinner, final MatchWinner realMatchWinner, final Boolean closed, 
            final Set<BetFragment> betFragments, final Set<BetFragment> betWins, final Set<BetFragment> betLoses, 
            final Integer points) {
        
        super();
        this.round = round;
        this.date = date;
        this.name = name;
        this.teamA = teamA;
        this.teamB = teamB;
        this.score = score;
        this.realScore = realScore;
        this.matchWinner = matchWinner;
        this.realMatchWinner = realMatchWinner;
        this.closed = closed;
        this.betFragments = new LinkedHashSet<BetFragment>(betFragments);
        this.betWins = new LinkedHashSet<BetFragment>(betWins);
        this.betLoses = new LinkedHashSet<BetFragment>(betLoses);
        this.points = points;
        
    }


    public RoundNew getRound() {
        return this.round;
    }


    public Calendar getDate() {
        return this.date;
    }


    public String getName() {
        return this.name;
    }


    public String getScore() {
        return this.score;
    }


    public String getRealScore() {
        return this.realScore;
    }


    public MatchWinner getMatchWinner() {
        return this.matchWinner;
    }


    public Integer getPoints() {
        return this.points;
    }


    public TeamNew getTeamA() {
        return this.teamA;
    }

    
    public TeamNew getTeamB() {
        return this.teamB;
    }


    public Boolean isClosed() {
        return this.closed;
    }


    public Set<BetFragment> getBetWins() {
        return this.betWins;
    }


    public MatchWinner getRealMatchWinner() {
        return this.realMatchWinner;
    }

    public Set<BetFragment> getBetFragments() {
        return this.betFragments;
    }

    public LinkedHashSet<BetFragment> getBetLoses() {
        return this.betLoses;
    }
    
}
