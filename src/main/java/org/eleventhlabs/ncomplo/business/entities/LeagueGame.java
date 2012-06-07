package org.eleventhlabs.ncomplo.business.entities;

import java.util.Comparator;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eleventhlabs.ncomplo.business.util.DatedAndNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;


@Entity
@Table(name="LEAGUE_GAME")
public class LeagueGame {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="LEAGUE_ID",nullable=false)
    private League league; 
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="GAME_ID",nullable=false)
    private Game game; 

    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="BET_TYPE_ID",nullable=false)
    private BetType betType; 



    
    
    public LeagueGame() {
        super();
    }





    public League getLeague() {
        return this.league;
    }



    public void setLeague(final League league) {
        this.league = league;
    }



    public Game getGame() {
        return this.game;
    }



    public void setGame(final Game game) {
        this.game = game;
    }


    public BetType getBetType() {
        return this.betType;
    }


    public void setBetType(final BetType betType) {
        this.betType = betType;
    }

    
    public Integer getId() {
        return this.id;
    }
    
    
    
    
    public static final class LeagueGameComparator implements Comparator<LeagueGame> {
        
        
        private final Locale locale;
        private final I18nNamedEntityComparator i18nNamedEntityComparator;
        private final DatedAndNamedEntityComparator datedAndNamedEntityComparator;
        
        
        public LeagueGameComparator(final Locale locale) {
            super();
            this.locale = locale;
            this.i18nNamedEntityComparator = new I18nNamedEntityComparator(this.locale);
            this.datedAndNamedEntityComparator = new DatedAndNamedEntityComparator(this.locale);
        }
        
        
        
        @Override
        public int compare(final LeagueGame o1, final LeagueGame o2) {
            
            final int leagueComp =
                    this.i18nNamedEntityComparator.compare(o1.getLeague(), o2.getLeague());
            if (leagueComp != 0) {
                return leagueComp;
            }

            return this.datedAndNamedEntityComparator.compare(o1.getGame(), o2.getGame());
            
        }
        
        
    }

    
}
