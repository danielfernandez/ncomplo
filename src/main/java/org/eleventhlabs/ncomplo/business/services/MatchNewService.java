package org.eleventhlabs.ncomplo.business.services;

import java.util.Calendar;
import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.entities.MatchNew;
import org.eleventhlabs.ncomplo.business.entities.RoundNew;
import org.eleventhlabs.ncomplo.business.entities.TeamNew;
import org.eleventhlabs.ncomplo.business.entities.repositories.MatchRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class MatchNewService {

    private static Sort SORT_BY_DATE = new Sort("date");
    
    
    @Autowired
    private MatchRepository matchRepository;
    
    
    
    public MatchNewService() {
        super();
    }
    
    
    @Transactional
    public List<MatchNew> findAllMatchesOrderByDate() {
//        return IterableUtils.toList(this.matchRepository.findAll(SORT_BY_DATE));
return null;
    }

    
    @Transactional
    public MatchNew getMatch(final Integer id) {
//        return this.matchRepository.findOne(id);
return null;
    }
    
    
    @Transactional
    public MatchNew addMatch(
            final String name, final BetType betType, final RoundNew round,
            final Calendar date, final TeamNew teamA, final TeamNew teamB, 
            final Integer scoreA, final Integer scoreB) {
        
        final MatchNew match = new MatchNew();
        match.setName(name);
        match.setBetType(betType);
        match.setRound(round);
        match.setDate(date);
        match.setTeamA(teamA);
        match.setTeamB(teamB);
        match.setScoreA(scoreA);
        match.setScoreB(scoreB);
        
//        return this.matchRepository.save(match);
return null;

    }
 
    
    @Transactional
    public MatchNew updateMatch(
            final Integer id,
            final String name, final BetType betType, final RoundNew round,
            final Calendar date, final TeamNew teamA, final TeamNew teamB, 
            final Integer scoreA, final Integer scoreB) {
        
//        final MatchNew match = this.matchRepository.findOne(id);
MatchNew match = null;
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
