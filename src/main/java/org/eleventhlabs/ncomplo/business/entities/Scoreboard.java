package org.eleventhlabs.ncomplo.business.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scoreboard implements Serializable {

    private static final long serialVersionUID = 3380033196402539211L;
    
    
    private final ArrayList<ScoreboardPosition> totalScores;
    private final ArrayList<ScoreboardPosition> groupStageScores;
    

    public Scoreboard() {
        super();
        this.totalScores = new ArrayList<ScoreboardPosition>();
        this.groupStageScores = new ArrayList<ScoreboardPosition>();
    }

    
    public void addScore(final String ownerName, final Integer totalPoints, final Integer groupStagePoints) {
        this.totalScores.add(new ScoreboardPosition(ownerName, totalPoints));
        this.groupStageScores.add(new ScoreboardPosition(ownerName, groupStagePoints));
        recompute(this.totalScores);
        recompute(this.groupStageScores);
    }

    
    private static void recompute(final ArrayList<ScoreboardPosition> positionList) {
        Collections.sort(positionList);
        for (int i = 0; i < positionList.size(); i++) {
            if (i == 0) {
                positionList.get(i).setNumber(Integer.valueOf(1));
            } else if (positionList.get(i - 1).getPoints().equals(positionList.get(i).getPoints())) {
                positionList.get(i).setNumber(positionList.get(i - 1).getNumber());
            } else {
                positionList.get(i).setNumber(Integer.valueOf(i + 1));
            }
        }
    }
    
 
    
    public List<ScoreboardPosition> getTotalScores() {
        return this.totalScores;
    }


    public List<ScoreboardPosition> getGroupStageScores() {
        return this.groupStageScores;
    }





    public static class ScoreboardPosition implements Serializable, Comparable<ScoreboardPosition> {
        
        private static final long serialVersionUID = 5548758378789500282L;
        
        private Integer number;
        private final String ownerName;
        private final Integer points;


        ScoreboardPosition(String ownerName, Integer points) {
            super();
            this.ownerName = ownerName;
            this.points = points;
        }

        public Integer getNumber() {
            return this.number;
        }
        
        public void setNumber(final Integer number) {
            this.number = number;
        }

        public String getOwnerName() {
            return this.ownerName;
        }

        public Integer getPoints() {
            return this.points;
        }

        @Override
        public int compareTo(ScoreboardPosition o) {
            final int pointComparison =
                o.points.compareTo(this.points);
            if (pointComparison != 0) {
                return pointComparison;
            }
            return this.ownerName.compareTo(o.ownerName);
        }
        
    }
    
}
