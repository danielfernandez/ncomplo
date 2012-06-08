package org.eleventhlabs.ncomplo.business.entities.repositories;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Bet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BetRepository 
        extends PagingAndSortingRepository<Bet,Integer> {
    
    public List<Bet> findByLeagueIdAndUserLogin(final Integer leagueId, final String login);
    
}
    