package org.eleventhlabs.ncomplo.business.entities;

import java.util.Comparator;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eleventhlabs.ncomplo.business.entities.LeagueGame.LeagueGameComparator;


@Entity
@Table(name="BET")
public class Bet {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID",nullable=false)
    private User user; 
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="LEAGUE_GAME_ID",nullable=false)
    private LeagueGame leagueGame; 

    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="GAME_SIDE_A_ID",nullable=true)
    private GameSide gameSideA;

    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="GAME_SIDE_B_ID",nullable=true)
    private GameSide gameSideB;
    
    
    @Column(name="SCORE_A",nullable=true)
    private Integer scoreA;

    
    @Column(name="SCORE_B",nullable=true)
    private Integer scoreB;


    @Column(name="POINTS_EARNED",nullable=true)
    private Integer pointsEarned;
    
    
    @Column(name="GLOBAL_WIN_LEVEL",nullable=true)
    private Integer globalWinLevel;
    
    
    @Column(name="SIDE_A_WIN_LEVEL",nullable=true)
    private Integer sideAWinLevel;
    
    
    @Column(name="SIDE_B_WIN_LEVEL",nullable=true)
    private Integer sideBWinLevel;
    
    
    @Column(name="SCORE_A_WIN_LEVEL",nullable=true)
    private Integer scoreAWinLevel;
    
    
    @Column(name="SCORE_B_WIN_LEVEL",nullable=true)
    private Integer scoreBWinLevel;


    
    
    public Bet() {
        super();
    }





    public LeagueGame getLeagueGame() {
        return this.leagueGame;
    }


    public void setLeagueGame(final LeagueGame leagueGame) {
        this.leagueGame = leagueGame;
    }


    public GameSide getGameSideA() {
        return this.gameSideA;
    }


    public void setGameSideA(final GameSide gameSideA) {
        this.gameSideA = gameSideA;
    }


    public GameSide getGameSideB() {
        return this.gameSideB;
    }


    public void setGameSideB(final GameSide gameSideB) {
        this.gameSideB = gameSideB;
    }


    public Integer getScoreA() {
        return this.scoreA;
    }


    public void setScoreA(final Integer scoreA) {
        this.scoreA = scoreA;
    }


    public Integer getScoreB() {
        return this.scoreB;
    }


    public void setScoreB(final Integer scoreB) {
        this.scoreB = scoreB;
    }


    public Integer getId() {
        return this.id;
    }
    
    
    public User getUser() {
        return this.user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Integer getPointsEarned() {
        return this.pointsEarned;
    }


    public void setPointsEarned(final Integer pointsEarned) {
        this.pointsEarned = pointsEarned;
    }


    public Integer getSideAWinLevel() {
        return this.sideAWinLevel;
    }


    public void setSideAWinLevel(final Integer sideAWinLevel) {
        this.sideAWinLevel = sideAWinLevel;
    }


    public Integer getSideBWinLevel() {
        return this.sideBWinLevel;
    }


    public void setSideBWinLevel(final Integer sideBWinLevel) {
        this.sideBWinLevel = sideBWinLevel;
    }


    public Integer getScoreAWinLevel() {
        return this.scoreAWinLevel;
    }


    public void setScoreAWinLevel(final Integer scoreAWinLevel) {
        this.scoreAWinLevel = scoreAWinLevel;
    }


    public Integer getScoreBWinLevel() {
        return this.scoreBWinLevel;
    }


    public void setScoreBWinLevel(final Integer scoreBWinLevel) {
        this.scoreBWinLevel = scoreBWinLevel;
    }


    public Integer getGlobalWinLevel() {
        return this.globalWinLevel;
    }


    public void setGlobalWinLevel(final Integer globalWinLevel) {
        this.globalWinLevel = globalWinLevel;
    }





    public void evaluate() {
        
        
    }
    
    
    
    
    public static final class BetComparator implements Comparator<Bet> {
        
        
        private final Locale locale;
        private final LeagueGameComparator leagueGameComparator;
        
        
        public BetComparator(final Locale locale) {
            super();
            this.locale = locale;
            this.leagueGameComparator = new LeagueGameComparator(this.locale);
        }
        
        
        
        @Override
        public int compare(final Bet o1, final Bet o2) {
            final int leagueGameComp =
                    this.leagueGameComparator.compare(o1.getLeagueGame(), o2.getLeagueGame());
            if (leagueGameComp != 0) {
                return leagueGameComp;
            }
            return o1.getUser().compareTo(o2.getUser());
        }
        
        
    }
    
    
}
