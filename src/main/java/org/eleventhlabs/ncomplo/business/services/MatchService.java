package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Match;
import org.eleventhlabs.ncomplo.business.entities.Team;
import org.eleventhlabs.ncomplo.business.entities.repositories.BetTypeRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.CompetitionRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.MatchRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.RoundRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.TeamRepository;
import org.eleventhlabs.ncomplo.business.util.DatedAndNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class MatchService {
    
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private BetTypeRepository betTypeRepository;
    
    @Autowired
    private RoundRepository roundRepository;
    
    @Autowired
    private TeamRepository teamRepository;

    
    
    
    public MatchService() {
        super();
    }
    
    
    
    @Transactional
    public Match find(final Integer id) {
        return this.matchRepository.findOne(id);
    }
    
    
    @Transactional
    public List<Match> findAllOrderByName(final Integer competitionId, final Locale locale) {
        final List<Match> rounds = 
                IterableUtils.toList(this.matchRepository.findByCompetitionId(competitionId));
        Collections.sort(rounds, new DatedAndNamedEntityComparator(locale));
        return rounds;
    }

    
    @Transactional
    public Match save(
            final Integer id,
            final Integer competitionId,
            final Date date,
            final String defaultName,
            final Map<String,String> namesByLang,
            final Integer defaultBetTypeId,
            final Integer roundId,
            final Integer teamAId,
            final Integer teamBId,
            final Integer scoreA,
            final Integer scoreB) {

        final Competition competition = 
                this.competitionRepository.findOne(competitionId);

        final Match match =
                (id == null? new Match() : this.matchRepository.findOne(id));
        
        final Team teamA = 
                (teamAId == null? null : this.teamRepository.findOne(teamAId));
        final Team teamB = 
                (teamBId == null? null : this.teamRepository.findOne(teamBId));
        
        match.setCompetition(competition);
        match.setDate(date);
        match.setName(defaultName);
        match.getNamesByLang().clear();
        match.getNamesByLang().putAll(namesByLang);
        match.setDefaultBetType(this.betTypeRepository.findOne(defaultBetTypeId));
        match.setRound(this.roundRepository.findOne(roundId));
        match.setTeamA(teamA);
        match.setTeamB(teamB);
        match.setScoreA(scoreA);
        match.setScoreB(scoreB);
        
        if (id == null) {
            competition.getMatches().add(match);
            return this.matchRepository.save(match);
        }
        return match;
        
    }
    
    
    
    @Transactional
    public void delete(final Integer matchId) {
        
        final Match match = 
                this.matchRepository.findOne(matchId);
        final Competition competition = match.getCompetition();
        
        competition.getMatches().remove(match);
        
    }

    
}
