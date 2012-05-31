package org.eleventhlabs.ncomplo.business.entities;

public enum BetFragment {

    TEAM_A("Equipo A"), 
    TEAM_B("Equipo B"),
    SCORE("Goles"),
    MATCH_WINNER("Ganador");

    
    public static BetFragment[] ALL_BET_WINS = 
        new BetFragment[] { TEAM_A, TEAM_B, SCORE, MATCH_WINNER };

    
    
    private final String localizedName;
    
    private BetFragment(final String localizedName) {
        this.localizedName = localizedName;
    }


    @Override
    public String toString() {
        return this.localizedName;
    }
    
}
