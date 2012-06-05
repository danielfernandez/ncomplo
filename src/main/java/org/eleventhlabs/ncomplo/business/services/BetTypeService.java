package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.repositories.BetTypeRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class BetTypeService {
    
    
    @Autowired
    private CompetitionRepository competitionRepository;

   
    @Autowired
    private BetTypeRepository betTypeRepository;
    
    
    
    public BetTypeService() {
        super();
    }
    
    
    @Transactional
    public BetType find(final Integer id) {
        return this.betTypeRepository.findOne(id);
    }
    
    
    @Transactional
    public List<BetType> findAllOrderByName(final Integer competitionId, final Locale locale) {
        final List<BetType> betTypes = 
                IterableUtils.toList(this.betTypeRepository.findByCompetitionId(competitionId));
        Collections.sort(betTypes, new Comparator<BetType>() {
            @Override
            public int compare(final BetType o1, final BetType o2) {
                final String o1Name = o1.getName(locale);
                final String o2Name = o2.getName(locale);
                if (o1Name == null) {
                    return 1;
                }
                if (o2Name == null) {
                    return -1;
                }
                return o1Name.compareTo(o2Name);
            }
        });
        return betTypes;
    }

    
    @Transactional
    public BetType save(
            final Integer id,
            final Integer competitionId,
            final String defaultName,
            final Map<String,String> namesByLang) {

        final Competition competition = 
                this.competitionRepository.findOne(competitionId);
                
        final BetType betType =
                (id == null? new BetType() : this.betTypeRepository.findOne(id));
        
        betType.setCompetition(competition);
        betType.setName(defaultName);
        betType.getNamesByLang().clear();
        betType.getNamesByLang().putAll(namesByLang);
        
        if (id == null) {
            competition.getBetTypes().add(betType);
            return this.betTypeRepository.save(betType);
        }
        return betType;
        
    }
    
    
    
    @Transactional
    public void delete(final Integer betTypeId) {
        
        final BetType betType = 
                this.betTypeRepository.findOne(betTypeId);
        final Competition competition = betType.getCompetition();
        
        competition.getBetTypes().remove(betType);
        
    }

    
}
