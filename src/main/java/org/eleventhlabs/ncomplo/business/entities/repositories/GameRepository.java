package org.eleventhlabs.ncomplo.business.entities.repositories;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Game;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepository extends PagingAndSortingRepository<Game,Integer> {
    
    public List<Game> findByCompetitionId(final Integer competitionId);
    
}
    