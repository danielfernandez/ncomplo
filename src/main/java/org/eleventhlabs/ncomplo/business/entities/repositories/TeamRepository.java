package org.eleventhlabs.ncomplo.business.entities.repositories;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Team;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository 
        extends PagingAndSortingRepository<Team,Integer> {
    
    public List<Team> findByCompetitionId(final Integer competitionId);
    
}
    