package org.eleventhlabs.ncomplo.web.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.eleventhlabs.ncomplo.exceptions.InternalErrorException;
import org.eleventhlabs.ncomplo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    
    
    @Autowired
    private UserService userService;

    
    
    public AuthController() {
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
            final Set<League> leagues = user.getLeagues();
            for (final League league : leagues) {
                if (league.isActive()) {
                    return "redirect:/scoreboard";
                }
            }
            
            if (user.isAdmin()) {
                return "redirect:/admin";
            }
            
        }
        
        return "redirect:/login";
        
    }

    

    @RequestMapping("/password")
    public String password(final HttpServletRequest request, final ModelMap model) {
        
        final String login = SessionUtil.getAuthenticatedUser(request);
        final User user = this.userService.find(login);
        
        model.addAttribute("user", user);
        
        return "changepassword";
        
    }



    
    @RequestMapping("/changepassword")
    public String changepassword(
            @RequestParam(value="oldPassword",required=true) String oldPassword,
            @RequestParam(value="newPassword1",required=true) String newPassword1,
            @RequestParam(value="newPassword2",required=true) String newPassword2,
            final HttpServletRequest request) {
        
        final String login = SessionUtil.getAuthenticatedUser(request);
        
        if (!newPassword1.equals(newPassword2)) {
            throw new InternalErrorException("New passwords do not match!");
        }

        this.userService.changePassword(login, oldPassword, newPassword1);
        
        return "redirect:/scoreboard";
        
    }

    
}
