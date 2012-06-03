package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CompetitionService {
    
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    
    
    public CompetitionService() {
        super();
    }
    
    
    @Transactional
    public Competition find(final Integer id) {
        return this.competitionRepository.findOne(id);
    }
    
    
    @Transactional
    public List<Competition> findAllOrderByName(final Locale locale) {
        final List<Competition> competitions = IterableUtils.toList(this.competitionRepository.findAll());
        Collections.sort(competitions, new Comparator<Competition>() {
            @Override
            public int compare(final Competition o1, final Competition o2) {
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
        return competitions;
    }

    
    @Transactional
    public Competition addCompetition(final Map<String,String> names, final boolean active) {
        
        final Competition competition = new Competition();
        competition.getNames().putAll(names);
        competition.setActive(active);
        
        final Competition result = this.competitionRepository.save(competition);

        throw new RuntimeException("Boooom!!");
    }
    

    @Transactional
    public Competition modifyCompetition(final Integer competitionId, final Map<String,String> names,
            final boolean active) {
        
        final Competition competition = 
                this.competitionRepository.findOne(competitionId);
        competition.getNames().clear();
System.out.println("PUTTING NAMES: " + names);
        competition.getNames().putAll(names);
        competition.setActive(active);
        
        return competition;
        
    }
    
    
    @Transactional
    public void deleteCompetition(final Integer competitionId) {
        this.competitionRepository.delete(competitionId);
    }

    
}
