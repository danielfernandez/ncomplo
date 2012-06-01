package org.eleventhlabs.ncomplo.business.entities.repositories;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompetitionRepository 
        extends PagingAndSortingRepository<Competition,Integer> {
    
    // No methods to add
    
}
    