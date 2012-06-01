package org.eleventhlabs.ncomplo.business.entities.repositories;

import org.eleventhlabs.ncomplo.business.entities.League;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeagueRepository 
        extends PagingAndSortingRepository<League,Integer> {
    
    // No methods to add
    
}
    