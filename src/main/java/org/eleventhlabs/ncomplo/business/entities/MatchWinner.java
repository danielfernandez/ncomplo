package org.eleventhlabs.ncomplo.business.entities;

public enum MatchWinner {

    TEAM_A("Equipo A"), 
    TEAM_B("Equipo B"),
    DRAW("Empate");

    
    public static MatchWinner[] ALL_MATCH_WINNERS = 
        new MatchWinner[] { TEAM_A, TEAM_B, DRAW };
    
    public static MatchWinner[] FINAL_MATCH_WINNERS = 
        new MatchWinner[] { TEAM_A, TEAM_B };

    
    
    private final String localizedName;
    
    private MatchWinner(final String localizedName) {
        this.localizedName = localizedName;
    }


    @Override
    public String toString() {
        return this.localizedName;
    }
    
}
