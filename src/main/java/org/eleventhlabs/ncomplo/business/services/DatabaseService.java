package org.eleventhlabs.ncomplo.business.services;

import org.eleventhlabs.ncomplo.business.accessor.SQLAccessor;
import org.eleventhlabs.ncomplo.business.views.SQLQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DatabaseService {

    @Autowired
    private SQLAccessor sqlAccessor;
    

    
    
    public DatabaseService() {
        super();
    }
    
    

    // Not transactional
    public SQLQueryResult executeQuery(final String sqlQuery) {        
        return this.sqlAccessor.executeQuery(sqlQuery);
    }
    
    

    // Not transactional
    public int executeUpdate(final String sqlQuery) {        
        return this.sqlAccessor.executeUpdate(sqlQuery);
    }    
    
}
