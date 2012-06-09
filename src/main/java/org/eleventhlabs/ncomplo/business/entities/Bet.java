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
import javax.persistence.Transient;

import org.eleventhlabs.ncomplo.business.entities.User.UserComparator;
import org.eleventhlabs.ncomplo.business.util.DatedAndNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.util.JavaScriptBetEvaluator;
import org.eleventhlabs.ncomplo.business.util.JavaScriptBetEvaluator.BetEvalResult;


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
    @JoinColumn(name="LEAGUE_ID",nullable=false)
    private League league; 
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="GAME_ID",nullable=false)
    private Game game; 

    
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


//    @Column(name="POINTS_COMPUTED",nullable=true)
    @Transient
    private Boolean pointsComputed = Boolean.FALSE;


    @Column(name="POINTS",nullable=true)
    private Integer points;
    
    
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


    public Integer getPoints() {
        return this.points;
    }


    public void setPoints(final Integer points) {
        this.points = points;
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


    public Boolean getPointsComputed() {
        return this.pointsComputed;
    }


    public void setPointsComputed(Boolean pointsComputed) {
        this.pointsComputed = pointsComputed;
    }




    public void evaluate() {
        
        final BetEvalResult evalResult = JavaScriptBetEvaluator.evaluate(this);
        this.setPointsComputed(Boolean.valueOf(evalResult.isPointsAssigned()));
        this.setPoints(evalResult.getPoints());
        this.setGlobalWinLevel(evalResult.getGlobalBetWinLevel());
        this.setSideAWinLevel(evalResult.getSideAWinLevel());
        this.setSideBWinLevel(evalResult.getSideBWinLevel());
        this.setScoreAWinLevel(evalResult.getScoreAWinLevel());
        this.setScoreBWinLevel(evalResult.getScoreBWinLevel());
        
    }
    
    
    
    
    public static final class BetComparator implements Comparator<Bet> {
        
        
        private final Locale locale;
        private final I18nNamedEntityComparator i18nNamedEntityComparator;
        private final DatedAndNamedEntityComparator datedAndNamedEntityComparator;
        private final UserComparator userComparator;
        
        
        public BetComparator(final Locale locale) {
            super();
            this.locale = locale;
            this.i18nNamedEntityComparator = new I18nNamedEntityComparator(this.locale);
            this.datedAndNamedEntityComparator = new DatedAndNamedEntityComparator(this.locale);
            this.userComparator = new UserComparator(this.locale);
        }
        
        
        
        @Override
        public int compare(final Bet o1, final Bet o2) {
            
            final int leagueComp =
                    this.i18nNamedEntityComparator.compare(o1.getLeague(), o2.getLeague());
            if (leagueComp != 0) {
                return leagueComp;
            }

            final int gameComp =
                    this.datedAndNamedEntityComparator.compare(o1.getGame(), o2.getGame());
            if (gameComp != 0) {
                return gameComp;
            }
            
            return this.userComparator.compare(o1.getUser(),o2.getUser());
            
        }
        
        
    }
    
    
}
