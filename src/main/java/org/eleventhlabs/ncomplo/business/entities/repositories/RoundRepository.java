package org.eleventhlabs.ncomplo.business.entities.repositories;

import org.eleventhlabs.ncomplo.business.entities.Round;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoundRepository extends PagingAndSortingRepository<Round,Integer> {
    
    // No methods to add
    
}
    