package org.eleventhlabs.ncomplo.business.entities.repositories;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match,Integer> {
    
    public List<Match> findByCompetitionId(final Integer competitionId);
    
}
    