package org.eleventhlabs.ncomplo.business.util;

import java.util.ArrayList;
import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Bet;
import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Game;
import org.eleventhlabs.ncomplo.business.entities.GameSide;
import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.LeagueGame;
import org.eleventhlabs.ncomplo.business.entities.Round;
import org.eleventhlabs.ncomplo.exceptions.InternalErrorException;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JavaScriptBetEvaluator {

    
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptBetEvaluator.class);
    
    
    public static BetEvalResult evaluate(final Bet bet) {

        Context ctx = null;
                
        final League league = bet.getLeague();
        final Competition competition = league.getCompetition();
        final Game game = bet.getGame();
        final Round round = game.getRound();
        final LeagueGame leagueGame = league.getLeagueGames().get(game);
        final BetType betType = leagueGame.getBetType();

        try {
            
            final List<Game> allGames = new ArrayList<Game>(competition.getGames());
            final List<GameSide> allGameSides = new ArrayList<GameSide>(competition.getGameSides());
            final List<Game> allGamesInRound = new ArrayList<Game>();
            final List<GameSide> allGameSidesInRound = new ArrayList<GameSide>();
            final List<Game> allGamesInNextRounds = new ArrayList<Game>();
            final List<GameSide> allGameSidesInNextRounds = new ArrayList<GameSide>();
            final List<Game> allGamesInPreviousRounds = new ArrayList<Game>();
            final List<GameSide> allGameSidesInPreviousRounds = new ArrayList<GameSide>();
            
            final int roundOrder = round.getOrder().intValue();
            
            for (final Game iGame : allGames) {
                
                final Round iGameRound = iGame.getRound();
                final int iGameRoundOrder = iGameRound.getOrder().intValue();
                
                if (iGameRound.getId().equals(round.getId())) {
                    // Comparation here is made by round id and not order,
                    // because some rounds could be different but have the
                    // same order (e.g. final and third-place match)
                    
                    allGamesInRound.add(iGame);
                    allGameSidesInRound.add(iGame.getGameSideA());
                    allGameSidesInRound.add(iGame.getGameSideB());
                    
                } else if (iGameRoundOrder < roundOrder) {
                    
                    allGamesInPreviousRounds.add(iGame);
                    allGameSidesInPreviousRounds.add(iGame.getGameSideA());
                    allGameSidesInPreviousRounds.add(iGame.getGameSideB());
                    
                } else  if (iGameRoundOrder > roundOrder) {
                    
                    allGamesInNextRounds.add(iGame);
                    allGameSidesInNextRounds.add(iGame.getGameSideA());
                    allGameSidesInNextRounds.add(iGame.getGameSideB());
                    
                }
                
            }

            
            ctx = Context.enter();
            final Scriptable scope = ctx.initStandardObjects();
            
            
            ScriptableObject.putProperty(scope, "league", Context.javaToJS(league, scope));
            ScriptableObject.putProperty(scope, "competition", Context.javaToJS(competition, scope));
            ScriptableObject.putProperty(scope, "game", Context.javaToJS(game, scope));
            ScriptableObject.putProperty(scope, "round", Context.javaToJS(round, scope));
            ScriptableObject.putProperty(scope, "betType", Context.javaToJS(betType, scope));
            ScriptableObject.putProperty(scope, "allGames", Context.javaToJS(allGames, scope));
            ScriptableObject.putProperty(scope, "allGameSides", Context.javaToJS(allGameSides, scope));
            ScriptableObject.putProperty(scope, "allGamesInRound", Context.javaToJS(allGamesInRound, scope));
            ScriptableObject.putProperty(scope, "allGameSidesInRound", Context.javaToJS(allGameSidesInRound, scope));
            ScriptableObject.putProperty(scope, "allGamesInNextRounds", Context.javaToJS(allGamesInNextRounds, scope));
            ScriptableObject.putProperty(scope, "allGameSidesInNextRounds", Context.javaToJS(allGameSidesInNextRounds, scope));
            ScriptableObject.putProperty(scope, "allGamesInPreviousRounds", Context.javaToJS(allGamesInPreviousRounds, scope));
            ScriptableObject.putProperty(scope, "allGameSidesInPreviousRounds", Context.javaToJS(allGameSidesInPreviousRounds, scope));

            final BetEvalResult result = new BetEvalResult();
            
            ScriptableObject.putProperty(scope, "result", Context.javaToJS(result, scope));
            
            ctx.evaluateString(scope, betType.getSpec(), "<beteval>", 1, null);
            
            if (logger.isTraceEnabled()) {
                logger.trace("[NCOMPLO] SUCCESSFUL EVALUATION. " +
                        "[LEAGUE=\"" + league.getName() + "\" | " +
                        "USER=\"" + bet.getUser().getLogin() + "\" | " +
                        "GAME=\"" + game.getName() + "\" | " +
                        "BETTYPE=\"" + betType.getName() + "\"] RESULT: " +  result);
            }

            return result;
            
        } catch (final Exception e) {
            
            throw new InternalErrorException(
                    "Error evaluating bet of type \"" +
                    betType.getName() + "\" for user \"" +
                    bet.getUser().getLogin() + "\" in league \"" +
                    league.getName() + "\" for game \"" + game.getName() + "\"",e);
            
        } finally {
            
            if (ctx != null) {
                Context.exit();
            }
            
        }
        
    }
    

    
    
    public static final class BetEvalResult {
        
        
        private Integer points = Integer.valueOf(0);
        private Integer globalBetWinLevel = Integer.valueOf(0); 
        private Integer sideAWinLevel = Integer.valueOf(0);
        private Integer sideBWinLevel = Integer.valueOf(0);
        private Integer scoreAWinLevel = Integer.valueOf(0);
        private Integer scoreBWinLevel = Integer.valueOf(0);
        
        
        public BetEvalResult() {
            super();
        }

        public Integer getPoints() {
            return this.points;
        }

        public void setPoints(final Integer pointsEarned) {
            this.points = pointsEarned;
        }

        public Integer getGlobalBetWinLevel() {
            return this.globalBetWinLevel;
        }

        public void setGlobalBetWinLevel(final Integer globalBetWinLevel) {
            this.globalBetWinLevel = globalBetWinLevel;
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

        
        @Override
        public String toString() {
            return "BetEvalResult [points=" + this.points
                    + ", globalBetWinLevel=" + this.globalBetWinLevel
                    + ", sideAWinLevel=" + this.sideAWinLevel + ", sideBWinLevel="
                    + this.sideBWinLevel + ", scoreAWinLevel=" + this.scoreAWinLevel
                    + ", scoreBWinLevel=" + this.scoreBWinLevel + "]";
        }
        
        
    }
    
    
}
