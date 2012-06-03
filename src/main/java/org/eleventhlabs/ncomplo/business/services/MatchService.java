package org.eleventhlabs.ncomplo.business.services;

import java.util.Calendar;
import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.entities.Match;
import org.eleventhlabs.ncomplo.business.entities.Round;
import org.eleventhlabs.ncomplo.business.entities.Team;
import org.eleventhlabs.ncomplo.business.entities.repositories.MatchRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class MatchService {

    private static Sort SORT_BY_DATE = new Sort("date");
    
    
    @Autowired
    private MatchRepository matchRepository;
    
    
    
    public MatchService() {
        super();
    }
    
    
    @Transactional
    public List<Match> findAllMatchesOrderByDate() {
        return IterableUtils.toList(this.matchRepository.findAll(SORT_BY_DATE));
    }

    
    @Transactional
    public Match getMatch(final Integer id) {
        return this.matchRepository.findOne(id);
    }
    
    
    @Transactional
    public Match addMatch(
            final String name, final BetType betType, final Round round,
            final Calendar date, final Team teamA, final Team teamB, 
            final Integer scoreA, final Integer scoreB) {
        
        final Match match = new Match();
        match.setName(name);
        match.setBetType(betType);
        match.setRound(round);
        match.setDate(date);
        match.setTeamA(teamA);
        match.setTeamB(teamB);
        match.setScoreA(scoreA);
        match.setScoreB(scoreB);
        
        return this.matchRepository.save(match);

    }
 
    
    @Transactional
    public Match updateMatch(
            final Integer id,
            final String name, final BetType betType, final Round round,
            final Calendar date, final Team teamA, final Team teamB, 
            final Integer scoreA, final Integer scoreB) {
        
        final Match match = this.matchRepository.findOne(id);
        match.setName(name);
        match.setBetType(betType);
        match.setRound(round);
        match.setDate(date);
        match.setTeamA(teamA);
        match.setTeamB(teamB);
        match.setScoreA(scoreA);
        match.setScoreB(scoreB);

        return match;
        
    }

    
    @Transactional
    public void deleteMatch(final Integer id) {
        this.matchRepository.delete(id);
    }

}
