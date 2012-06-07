package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;
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
    public List<Competition> findAll(final Locale locale) {
        final List<Competition> competitions = IterableUtils.toList(this.competitionRepository.findAll());
        Collections.sort(competitions, new I18nNamedEntityComparator(locale));
        return competitions;
    }

    
    @Transactional
    public Competition save(
            final Integer id,
            final String name, 
            final Map<String,String> namesByLang, 
            final boolean active) {
        
        final Competition competition =
                (id == null? new Competition() : this.competitionRepository.findOne(id));

        competition.setName(name);
        competition.getNamesByLang().clear();
        competition.getNamesByLang().putAll(namesByLang);
        competition.setActive(active);
        
        if (id == null) {
            return this.competitionRepository.save(competition);
        }
        return competition;
        
    }
    

    
    @Transactional
    public void delete(final Integer competitionId) {
        this.competitionRepository.delete(competitionId);
    }

    
}
