package org.eleventhlabs.ncomplo.web.admin.controller;

import org.apache.commons.lang.StringUtils;
import org.eleventhlabs.ncomplo.business.services.DatabaseService;
import org.eleventhlabs.ncomplo.business.views.SQLQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin/database")
public class DatabaseController {

    private static final String VIEW_BASE = "/admin/database/";
    
    
    
    @Autowired
    private DatabaseService databaseService;

    
    
    
    public DatabaseController() {
        super();
    }
    

    
    
    @RequestMapping("/querydatabase")
    public String queryDatabase(
            @RequestParam(value="query",required=false) final String query,
            @RequestParam(value="update",required=false) final String update,
            final ModelMap model) {

        if (!StringUtils.isEmpty(query)) {
            
            final SQLQueryResult result = 
                    this.databaseService.executeQuery(query);
            
            model.addAttribute("queryResult", result);
            model.addAttribute("query", query);
            
        } else if (!StringUtils.isEmpty(update)) {
            
            final int result = 
                    this.databaseService.executeUpdate(update);
            
            model.addAttribute("updateResult", Integer.valueOf(result));
            model.addAttribute("update", update);
            
        }
        
        return VIEW_BASE + "querydatabase";
    }


    
}
