package org.eleventhlabs.ncomplo.business.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.eleventhlabs.ncomplo.business.entities.Bet;
import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.entities.repositories.BetRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.LeagueRepository;
import org.eleventhlabs.ncomplo.business.views.ScoreboardEntry;
import org.eleventhlabs.ncomplo.business.views.ScoreboardEntry.ScoreboardEntryComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ScoreboardService {
    
    
    @Autowired
    private LeagueRepository leagueRepository;
    
    @Autowired
    private BetRepository betRepository;
 
    
    
    
    public ScoreboardService() {
        super();
    }
    
    
    

    @Transactional
    public List<ScoreboardEntry> computeScoreboard(
            final Integer leagueId, final Integer roundId, final Locale locale) {

        
        final List<ScoreboardEntry> scoreboard = new ArrayList<ScoreboardEntry>();
        
        final League league = this.leagueRepository.findOne(leagueId);
        final Set<User> participants = league.getParticipants();
        final Calendar calendar = Calendar.getInstance();
        final Date currentDate = calendar.getTime();
        String game1SideA = null;
        Integer score1SideA = null;
        String game1SideB = null;
        Integer score1SideB = null;
        String game2SideA = null;
        Integer score2SideA = null;
        String game2SideB = null;
        Integer score2SideB = null;
        for (final User participant : participants) {
            
            int totalPoints = 0;
            
            final List<Bet> bets = 
                    this.betRepository.findByLeagueIdAndUserLogin(leagueId, participant.getLogin());
            game1SideA = null;
            score1SideA = null;
            game1SideB = null;
            score1SideB = null;
            game2SideA = null;
            score2SideA = null;
            game2SideB = null;
            score2SideB = null;
            for (final Bet bet : bets) {
            	if(DateUtils.isSameDay(currentDate, bet.getGame().getDate())){
					if (StringUtils.isBlank(game1SideA)
							&& StringUtils.isBlank(game1SideB)
							&& score1SideA == null && score1SideB == null) {
            			game1SideA=bet.getGameSideA().getName(locale);
            			game1SideB=bet.getGameSideB().getName(locale);
            			score1SideA=bet.getScoreA();
            			score1SideB=bet.getScoreB();
            		} else {
               			game2SideA=bet.getGameSideA().getName(locale);
            			game2SideB=bet.getGameSideB().getName(locale);
            			score2SideA=bet.getScoreA();
            			score2SideB=bet.getScoreB();
            		}
            	}
                if (bet.getPoints() != null) {
                    if (roundId != null) {
                        if (!bet.getGame().getRound().getId().equals(roundId)) {
                            continue;
                        }
                    }
                    totalPoints += bet.getPoints().intValue();
                }
            }
            
			scoreboard.add(new ScoreboardEntry(participant, Integer
					.valueOf(totalPoints), game1SideA, game1SideB, game2SideA,
					game2SideB, score1SideA, score1SideB, score2SideA,
					score2SideB));
            
        }
        
        Collections.sort(scoreboard, new ScoreboardEntryComparator(locale));
        
        int position = 0;
        int treated  = 0;
        int lastPoints = -1;
        for (final ScoreboardEntry entry : scoreboard) {
            treated++;
            if (entry.getPoints().intValue() != lastPoints) {
                position = treated;
                lastPoints = entry.getPoints().intValue();
            } 
            entry.setPosition(Integer.valueOf(position));                       
        }
        
        
        return scoreboard;
        
    }
    
}
