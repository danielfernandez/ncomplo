package org.eleventhlabs.ncomplo.business.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class BetResult implements Serializable {

    private static final long serialVersionUID = -688318849853967583L;
    
    private final Round round;
    private final String date;
    private final String name;
    private final Team teamA;
    private final Team teamB;
    private final String score;
    private final String realScore;
    private final MatchWinner matchWinner;
    private final MatchWinner realMatchWinner;
    private final Boolean closed;
    private final LinkedHashSet<BetFragment> betFragments;
    private final LinkedHashSet<BetFragment> betWins;
    private final LinkedHashSet<BetFragment> betLoses;
    private final Integer points;


    public BetResult(final Round round, final String date, final String name, 
            final Team teamA, final Team teamB, final String score, final String realScore, 
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


    public Round getRound() {
        return this.round;
    }


    public String getDate() {
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


    public Team getTeamA() {
        return this.teamA;
    }

    
    public Team getTeamB() {
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
