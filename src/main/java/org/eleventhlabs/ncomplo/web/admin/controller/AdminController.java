package org.eleventhlabs.ncomplo.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private static final String VIEW_BASE = "/admin/";

    
    
    
    public AdminController() {
        super();
    }
    



    
    
    @RequestMapping("/admin")
    public String index() {
        return VIEW_BASE + "index";
    }
    
    
}
