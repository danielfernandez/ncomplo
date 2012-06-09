package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.eleventhlabs.ncomplo.business.entities.Bet;
import org.eleventhlabs.ncomplo.business.entities.Bet.BetComparator;
import org.eleventhlabs.ncomplo.business.entities.Game;
import org.eleventhlabs.ncomplo.business.entities.GameSide;
import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.entities.repositories.BetRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.GameRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.GameSideRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.LeagueRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class BetService {
    
    
    @Autowired
    private LeagueRepository leagueRepository;
    
    @Autowired
    private BetRepository betRepository;
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private GameSideRepository gameSideRepository;
    
    @Autowired
    private UserRepository userRepository;
 
    
    
    
    public BetService() {
        super();
    }
    
    
    @Transactional
    public Bet find(final Integer id) {
        return this.betRepository.findOne(id);
    }
    
    
    @Transactional
    public List<Bet> findByLeagueIdAndUserLogin(
            final Integer leagueId, final String login, final Locale locale) {
        final List<Bet> bets = 
                this.betRepository.findByLeagueIdAndUserLogin(leagueId, login);
        Collections.sort(bets, new BetComparator(locale));
        return bets;
    }

    
    
    @Transactional
    public Bet save(
            final Integer id,
            final Integer leagueId,
            final String login,
            final Integer gameId,
            final Integer gameSideAId,
            final Integer gameSideBId,
            final Integer scoreA,
            final Integer scoreB) {

        final League league = 
                this.leagueRepository.findOne(leagueId);
        final User user = 
                this.userRepository.findOne(login);
        final Game game = 
                this.gameRepository.findOne(gameId);
        
        final GameSide gameSideA = 
                (gameSideAId == null? null : this.gameSideRepository.findOne(gameSideAId));
        final GameSide gameSideB = 
                (gameSideBId == null? null : this.gameSideRepository.findOne(gameSideBId));
        
        final Bet bet =
                (id == null? new Bet() : this.betRepository.findOne(id));

        bet.setLeague(league);
        bet.setGame(game);
        bet.setUser(user);
        bet.setGameSideA(gameSideA);
        bet.setGameSideB(gameSideB);
        bet.setScoreA(scoreA);
        bet.setScoreB(scoreB);
        
        if (id == null) {
            return this.betRepository.save(bet);
        }
        return bet;
        
    }
    

    
    @Transactional
    public void delete(final Integer betId) {
        this.betRepository.delete(betId);
    }

    
    
    
}
