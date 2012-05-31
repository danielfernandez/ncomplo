package org.eleventhlabs.ncomplo.business.entities;

public class BetDefinition {
    
    private final Integer matchId;
    private final Team teamA;
    private final Team teamB;
    private final Integer scoreA;
    private final Integer scoreB;
    private final MatchWinner matchWinner;

    
    public BetDefinition(final Integer matchId, final Team teamA, final Team teamB) {
        this(matchId, teamA, teamB, null, null, null);
    }

    public BetDefinition(final Integer matchId, final Integer scoreA, final Integer scoreB) {
        this(matchId, null, null, scoreA, scoreB, null);
    }

    public BetDefinition(final Integer matchId, final Team teamA, final Team teamB, final MatchWinner matchWinner) {
        this(matchId, teamA, teamB, null, null, matchWinner);
    }
    
    private BetDefinition(final Integer matchId, final Team teamA, final Team teamB, final Integer scoreA, final Integer scoreB, final MatchWinner matchWinner) {
        super();
        this.matchId = matchId;
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.matchWinner = matchWinner;
    }
    
    public Integer getMatchId() {
        return this.matchId;
    }

    public Team getTeamA() {
        return this.teamA;
    }

    public Team getTeamB() {
        return this.teamB;
    }

    public Integer getScoreA() {
        return this.scoreA;
    }

    public Integer getScoreB() {
        return this.scoreB;
    }

    public MatchWinner getMatchWinner() {
        return this.matchWinner;
    }
    
    
}
