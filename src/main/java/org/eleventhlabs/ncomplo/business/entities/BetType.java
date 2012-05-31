package org.eleventhlabs.ncomplo.business.entities;

public enum BetType {

    RESULT_AND_SCORE("Resultado y goles"), 
    PRESENCE_IN_ROUND("Presencia en ronda"), 
    FINAL("Final");

    
    public static BetType[] ALL_BET_TYPES = 
        new BetType[] { RESULT_AND_SCORE, PRESENCE_IN_ROUND, FINAL };

    
    
    private final String localizedName;
    
    private BetType(final String localizedName) {
        this.localizedName = localizedName;
    }


    @Override
    public String toString() {
        return this.localizedName;
    }
    
}
