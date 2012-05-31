package org.eleventhlabs.ncomplo.business.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BetResults implements Serializable {

    private static final long serialVersionUID = 5443576399117725729L;
    
    private final String ownerName;
    private final Integer groupStagePoints;
    private final Integer totalPoints;
    private final ArrayList<BetResult> betResults;


    public BetResults(final String ownerName, final Integer groupStagePoints,
            final Integer totalPoints, final List<BetResult> betResults) {
        
        super();
        this.ownerName = ownerName;
        this.groupStagePoints = groupStagePoints;
        this.totalPoints = totalPoints;
        this.betResults = new ArrayList<BetResult>(betResults);
        
    }


    public String getOwnerName() {
        return this.ownerName;
    }


    public Integer getGroupStagePoints() {
        return this.groupStagePoints;
    }


    public Integer getTotalPoints() {
        return this.totalPoints;
    }


    public List<BetResult> getBetResults() {
        return this.betResults;
    }
    
    
}
