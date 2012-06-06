package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Team;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.TeamRepository;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TeamService {
    
    
    @Autowired
    private CompetitionRepository competitionRepository;

   
    @Autowired
    private TeamRepository teamRepository;
    
    
    
    public TeamService() {
        super();
    }
    
    
    @Transactional
    public Team find(final Integer id) {
        return this.teamRepository.findOne(id);
    }
    
    
    @Transactional
    public List<Team> findAllOrderByName(final Integer competitionId, final Locale locale) {
        final List<Team> teams = 
                IterableUtils.toList(this.teamRepository.findByCompetitionId(competitionId));
        Collections.sort(teams, new I18nNamedEntityComparator(locale));
        return teams;
    }

    
    @Transactional
    public Team save(
            final Integer id,
            final Integer competitionId,
            final String defaultName,
            final Map<String,String> namesByLang) {

        final Competition competition = 
                this.competitionRepository.findOne(competitionId);
                
        final Team team =
                (id == null? new Team() : this.teamRepository.findOne(id));
        
        team.setCompetition(competition);
        team.setName(defaultName);
        team.getNamesByLang().clear();
        team.getNamesByLang().putAll(namesByLang);
        
        if (id == null) {
            competition.getTeams().add(team);
            return this.teamRepository.save(team);
        }
        return team;
        
    }
    
    
    
    @Transactional
    public void delete(final Integer teamId) {
        
        final Team team = 
                this.teamRepository.findOne(teamId);
        final Competition competition = team.getCompetition();
        
        competition.getRounds().remove(team);
        
    }

    
}
