package org.eleventhlabs.ncomplo.business.entities.repositories;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Bet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BetRepository extends CrudRepository<Bet, Integer> {
    
    public List<Bet> findByOwnerName(final String ownerName);
    
    @Modifying
    @Query("delete from Bet b where b.ownerName = ?1")
    public void deleteByOwnerName(final String ownerName);
    
}
    