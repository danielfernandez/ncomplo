package org.eleventhlabs.ncomplo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.eleventhlabs.ncomplo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {
    
    
    @Autowired
    private UserService userService;

    
    
    public BaseController() {
        super();
    }
    

    
    
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    
    
    @RequestMapping("/authenticate")
    public String authenticate(
            @RequestParam(value="login")
            final String login,
            @RequestParam(value="password")
            final String password,
            final HttpServletRequest request) {

        final User user = 
                this.userService.authenticate(login, password);
        if (user != null) {
            SessionUtil.setAuthenticatedUser(request, login, user.isAdmin());
            if (user.isAdmin()) {
                return "redirect:/admin";
            }
            return "redirect:/scoreboard";
        }
        
        return "redirect:/login";
        
    }
    
}
