package org.eleventhlabs.ncomplo.business.entities.repositories;

import org.eleventhlabs.ncomplo.business.entities.BetNew;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BetNewRepository 
        extends CrudRepository<BetNew, Integer> {
    
}
    