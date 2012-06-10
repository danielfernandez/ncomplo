package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Game;
import org.eleventhlabs.ncomplo.business.entities.Game.GameComparator;
import org.eleventhlabs.ncomplo.business.entities.GameSide;
import org.eleventhlabs.ncomplo.business.entities.repositories.BetTypeRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.GameRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.GameSideRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.RoundRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class GameService {
    
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private BetTypeRepository betTypeRepository;
    
    @Autowired
    private RoundRepository roundRepository;
    
    @Autowired
    private GameSideRepository gameSideRepository;
    
    
    
    
    
    public GameService() {
        super();
    }
    
    
    
    
    
    @Transactional
    public Game find(final Integer id) {
        return this.gameRepository.findOne(id);
    }
    
    
    @Transactional
    public List<Game> findAll(final Integer competitionId, final Locale locale) {
        final List<Game> rounds = 
                IterableUtils.toList(this.gameRepository.findByCompetitionId(competitionId));
        Collections.sort(rounds, new GameComparator(locale));
        return rounds;
    }

    
    @Transactional
    public Game save(
            final Integer id,
            final Integer competitionId,
            final Date date,
            final String name,
            final Map<String,String> namesByLang,
            final Integer defaultBetTypeId,
            final Integer roundId,
            final Integer order,
            final Integer gameSideAId,
            final Integer gameSideBId,
            final Integer scoreA,
            final Integer scoreB) {

        final Competition competition = 
                this.competitionRepository.findOne(competitionId);

        final Game game =
                (id == null? new Game() : this.gameRepository.findOne(id));
        
        final GameSide gameSideA = 
                (gameSideAId == null? null : this.gameSideRepository.findOne(gameSideAId));
        final GameSide gameSideB = 
                (gameSideBId == null? null : this.gameSideRepository.findOne(gameSideBId));
        
        game.setCompetition(competition);
        game.setDate(date);
        game.setName(name);
        game.getNamesByLang().clear();
        game.getNamesByLang().putAll(namesByLang);
        game.setDefaultBetType(this.betTypeRepository.findOne(defaultBetTypeId));
        game.setRound(this.roundRepository.findOne(roundId));
        game.setOrder(order);
        game.setGameSideA(gameSideA);
        game.setGameSideB(gameSideB);
        game.setScoreA(scoreA);
        game.setScoreB(scoreB);
        
        if (id == null) {
            competition.getGames().add(game);
            return this.gameRepository.save(game);
        }
        
        return game;
        
    }
    
    
    
    @Transactional
    public void delete(final Integer gameId) {
        
        final Game game = 
                this.gameRepository.findOne(gameId);
        final Competition competition = game.getCompetition();
        
        competition.getGames().remove(game);
        
    }
    
    
}
