package org.eleventhlabs.ncomplo.business.entities.repositories;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BetTypeRepository 
        extends PagingAndSortingRepository<BetType,Integer> {
    
    public List<BetType> findByCompetitionId(final Integer competitionId);
    
}
    