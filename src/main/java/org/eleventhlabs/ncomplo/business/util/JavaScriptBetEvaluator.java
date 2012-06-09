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
            
            GameSide gameWinner = null;
            Boolean gameDraw = null;
            if (game.getScoreA() != null && game.getScoreB() != null) {
                final Integer scoreA = game.getScoreA();
                final Integer scoreB = game.getScoreB();
                if (scoreA.equals(scoreB)) {
                    gameDraw = Boolean.TRUE;
                } else if (scoreA.intValue() > scoreB.intValue()) {
                    gameWinner = game.getGameSideA();
                    gameDraw = Boolean.FALSE;
                } else {
                    gameWinner = game.getGameSideB();
                    gameDraw = Boolean.FALSE;
                }
            }

            
            GameSide betWinner = null;
            Boolean betDraw = null;
            if (bet.getScoreA() != null && bet.getScoreB() != null) {
                final Integer scoreA = bet.getScoreA();
                final Integer scoreB = bet.getScoreB();
                if (scoreA.equals(scoreB)) {
                    betDraw = Boolean.TRUE;
                } else if (scoreA.intValue() > scoreB.intValue()) {
                    betWinner = bet.getGameSideA();
                    betDraw = Boolean.FALSE;
                } else {
                    betWinner = bet.getGameSideB();
                    betDraw = Boolean.FALSE;
                }
            }
            
            Boolean gameSidesDefined =
                    Boolean.valueOf(game.getGameSideA() != null && game.getGameSideB() != null);
            Boolean betSidesDefined =
                    Boolean.valueOf(bet.getGameSideA() != null && bet.getGameSideB() != null);
            
            Boolean gameScoresDefined =
                    Boolean.valueOf(game.getScoreA() != null && game.getScoreB() != null);
            Boolean betScoresDefined =
                    Boolean.valueOf(bet.getScoreA() != null && bet.getScoreB() != null);
            

            
            ctx = Context.enter();
            final Scriptable scope = ctx.initStandardObjects();
            
            
            ScriptableObject.putProperty(scope, "bet", Context.javaToJS(bet, scope));
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
            ScriptableObject.putProperty(scope, "betWinner", Context.javaToJS(betWinner, scope));
            ScriptableObject.putProperty(scope, "gameWinner", Context.javaToJS(gameWinner, scope));
            ScriptableObject.putProperty(scope, "betDraw", Context.javaToJS(betDraw, scope));
            ScriptableObject.putProperty(scope, "gameDraw", Context.javaToJS(gameDraw, scope));
            ScriptableObject.putProperty(scope, "betSidesDefined", Context.javaToJS(betSidesDefined, scope));
            ScriptableObject.putProperty(scope, "gameSidesDefined", Context.javaToJS(gameSidesDefined, scope));
            ScriptableObject.putProperty(scope, "betScoresDefined", Context.javaToJS(betScoresDefined, scope));
            ScriptableObject.putProperty(scope, "gameScoresDefined", Context.javaToJS(gameScoresDefined, scope));

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
        
        
        private Integer points = null;
        private Integer globalBetWinLevel = Integer.valueOf(0); 
        private Integer sideAWinLevel = Integer.valueOf(0);
        private Integer sideBWinLevel = Integer.valueOf(0);
        private Integer scoreAWinLevel = Integer.valueOf(0);
        private Integer scoreBWinLevel = Integer.valueOf(0);
        private boolean pointsAssigned = false;
        
        
        public BetEvalResult() {
            super();
        }

        public Integer getPoints() {
            if (!this.pointsAssigned) {
                return Integer.valueOf(0);
            }
            return this.points;
        }

        public void setPoints(final Integer pointsEarned) {
            // This way we can know whether a bet has already
            // been really computed.
            this.points = pointsEarned;
            this.pointsAssigned = true;
        }
        
        public boolean isPointsAssigned() {
            return this.pointsAssigned;
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
