package org.eleventhlabs.ncomplo.business.views;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import org.eleventhlabs.ncomplo.business.entities.User;



public class ScoreboardEntry {

    private Integer position = null;
    private User user;
    private Integer points;
    private String game1SideA;
    private Integer score1SideA;
    private String game1SideB;
    private Integer score1SideB;
    private String game2SideA;
    private Integer score2SideA;
    private String game2SideB;
    private Integer score2SideB;
    
	public ScoreboardEntry(final User user, final Integer points,
			String game1SideA, String game1SideB, String game2SideA,
			String game2SideB, Integer score1SideA, Integer score1SideB,
			Integer score2SideA, Integer score2SideB) {
        super();
        this.user = user;
		this.points = points;
		this.game1SideA = game1SideA;
		this.game1SideB = game1SideB;
		this.game2SideA = game2SideA;
		this.game2SideB = game2SideB;
		this.score1SideA = score1SideA;
		this.score1SideB = score1SideB;
		this.score2SideA = score2SideA;
		this.score2SideB = score2SideB;
    }

    

    public Integer getPosition() {
        return this.position;
    }


    public void setPosition(final Integer position) {
        this.position = position;
    }


    public User getUser() {
        return this.user;
    }


    public Integer getPoints() {
        return this.points;
    }
    

    public String getGame1SideA() {
		return game1SideA;
	}



	public void setGame1SideA(String game1SideA) {
		this.game1SideA = game1SideA;
	}



	public Integer getScore1SideA() {
		return score1SideA;
	}



	public void setScore1SideA(Integer score1SideA) {
		this.score1SideA = score1SideA;
	}



	public String getGame1SideB() {
		return game1SideB;
	}



	public void setGame1SideB(String game1SideB) {
		this.game1SideB = game1SideB;
	}



	public Integer getScore1SideB() {
		return score1SideB;
	}



	public void setScore1SideB(Integer score1SideB) {
		this.score1SideB = score1SideB;
	}



	public String getGame2SideA() {
		return game2SideA;
	}



	public void setGame2SideA(String game2SideA) {
		this.game2SideA = game2SideA;
	}



	public Integer getScore2SideA() {
		return score2SideA;
	}



	public void setScore2SideA(Integer score2SideA) {
		this.score2SideA = score2SideA;
	}



	public String getGame2SideB() {
		return game2SideB;
	}



	public void setGame2SideB(String game2SideB) {
		this.game2SideB = game2SideB;
	}



	public Integer getScore2SideB() {
		return score2SideB;
	}



	public void setScore2SideB(Integer score2SideB) {
		this.score2SideB = score2SideB;
	}




	public static class ScoreboardEntryComparator implements Comparator<ScoreboardEntry> {
        
        private final Locale locale;
        
        public ScoreboardEntryComparator(final Locale locale) {
            super();
            this.locale = locale;
        }
        
        @Override
        public int compare(final ScoreboardEntry o1, final ScoreboardEntry o2) {
            final int posComp = o1.getPoints().compareTo(o2.getPoints());
            if (posComp != 0) {
                return -1 * posComp;
            }
            final Collator collator = Collator.getInstance(this.locale);
            return collator.compare(o1.getUser().getName(), o2.getUser().getName());
        }
        
    }
    
    
}
