package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.BetType;
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
    public BetType add(final Map<String,String> names, final Integer competitionId) {
        
        final BetType betType = new BetType();
        betType.getNames().putAll(names);
        betType.setCompetition(this.competitionRepository.findOne(competitionId));
        
        return this.betTypeRepository.save(betType);
        
    }
    
    
    
    @Transactional
    public void delete(final Integer betTypeId) {
        this.betTypeRepository.delete(betTypeId);
    }

    
}
