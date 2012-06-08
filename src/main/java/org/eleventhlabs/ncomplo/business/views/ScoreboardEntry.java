package org.eleventhlabs.ncomplo.business.views;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import org.eleventhlabs.ncomplo.business.entities.User;



public class ScoreboardEntry {

    
    private Integer position;
    private User user;
    private Integer points;

    
    public ScoreboardEntry(final  Integer position, final User user, final Integer points) {
        super();
        this.position = position;
        this.user = user;
        this.points = points;
    }


    public Integer getPosition() {
        return this.position;
    }


    public User getUser() {
        return this.user;
    }


    public Integer getPoints() {
        return this.points;
    }

    
    

    public static class ScoreboardEntryComparator implements Comparator<ScoreboardEntry> {
        
        private final Locale locale;
        
        public ScoreboardEntryComparator(final Locale locale) {
            super();
            this.locale = locale;
        }
        
        @Override
        public int compare(final ScoreboardEntry o1, final ScoreboardEntry o2) {
            final int posComp = o1.getPosition().compareTo(o2.getPosition());
            if (posComp != 0) {
                return posComp;
            }
            final Collator collator = Collator.getInstance(this.locale);
            return collator.compare(o1.getUser().getName(), o2.getUser().getName());
        }
        
    }
    
    
}
