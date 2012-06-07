package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.GameSide;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.GameSideRepository;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class GameSideService {
    
    
    @Autowired
    private CompetitionRepository competitionRepository;

   
    @Autowired
    private GameSideRepository gameSideRepository;
    
    
    
    public GameSideService() {
        super();
    }
    
    
    @Transactional
    public GameSide find(final Integer id) {
        return this.gameSideRepository.findOne(id);
    }
    
    
    @Transactional
    public List<GameSide> findAll(final Integer competitionId, final Locale locale) {
        final List<GameSide> gameSides = 
                IterableUtils.toList(this.gameSideRepository.findByCompetitionId(competitionId));
        Collections.sort(gameSides, new I18nNamedEntityComparator(locale));
        return gameSides;
    }

    
    @Transactional
    public GameSide save(
            final Integer id,
            final Integer competitionId,
            final String name,
            final Map<String,String> namesByLang) {

        final Competition competition = 
                this.competitionRepository.findOne(competitionId);
                
        final GameSide gameSide =
                (id == null? new GameSide() : this.gameSideRepository.findOne(id));
        
        gameSide.setCompetition(competition);
        gameSide.setName(name);
        gameSide.getNamesByLang().clear();
        gameSide.getNamesByLang().putAll(namesByLang);
        
        if (id == null) {
            competition.getGameSides().add(gameSide);
            return this.gameSideRepository.save(gameSide);
        }
        return gameSide;
        
    }
    
    
    
    @Transactional
    public void delete(final Integer gameSideId) {
        
        final GameSide gameSide = 
                this.gameSideRepository.findOne(gameSideId);
        final Competition competition = gameSide.getCompetition();
        
        competition.getRounds().remove(gameSide);
        
    }

    
}
