package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Round;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.RoundRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RoundService {
    
    
    @Autowired
    private CompetitionRepository competitionRepository;

   
    @Autowired
    private RoundRepository roundRepository;
    
    
    
    public RoundService() {
        super();
    }
    
    
    @Transactional
    public Round find(final Integer id) {
        return this.roundRepository.findOne(id);
    }
    
    
    @Transactional
    public List<Round> findAll(final Integer competitionId) {
        final List<Round> rounds = 
                IterableUtils.toList(this.roundRepository.findByCompetitionId(competitionId));
        Collections.sort(rounds);
        return rounds;
    }

    
    @Transactional
    public Round save(
            final Integer id,
            final Integer competitionId,
            final String name,
            final Map<String,String> namesByLang,
            final Integer order) {

        final Competition competition = 
                this.competitionRepository.findOne(competitionId);
                
        final Round round =
                (id == null? new Round() : this.roundRepository.findOne(id));
        
        round.setCompetition(competition);
        round.setName(name);
        round.getNamesByLang().clear();
        round.getNamesByLang().putAll(namesByLang);
        round.setOrder(order);
        
        if (id == null) {
            competition.getRounds().add(round);
            return this.roundRepository.save(round);
        }
        return round;
        
    }
    
    
    
    @Transactional
    public void delete(final Integer roundId) {
        
        final Round round = 
                this.roundRepository.findOne(roundId);
        final Competition competition = round.getCompetition();
        
        competition.getRounds().remove(round);
        
    }

    
}
