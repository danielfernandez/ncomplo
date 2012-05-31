package org.eleventhlabs.ncomplo.business.entities;

public enum Round {

    GROUP_STAGE("Fase de Grupos"), 
    ROUND_OF_16("Octavos de Final"), 
    QUARTER_FINALS("Cuartos de Final"), 
    SEMI_FINALS("Semifinales"), 
    FINAL("Final"); 


    public static Round[] ALL_ROUNDS = 
        new Round[] { GROUP_STAGE, ROUND_OF_16, QUARTER_FINALS, SEMI_FINALS, FINAL };

    
    private final String localizedName;
    
    private Round(final String localizedName) {
        this.localizedName = localizedName;
    }

    @Override
    public String toString() {
        return this.localizedName;
    }
    
}
