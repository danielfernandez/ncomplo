package org.eleventhlabs.ncomplo.business.entities.repositories;

import org.eleventhlabs.ncomplo.business.entities.LeagueGame;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeagueGameRepository 
        extends PagingAndSortingRepository<LeagueGame,Integer> {
    
    // No methods to add
    
}
    