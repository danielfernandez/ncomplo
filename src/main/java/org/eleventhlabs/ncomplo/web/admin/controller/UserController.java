package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.services.LeagueService;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.eleventhlabs.ncomplo.web.admin.beans.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private static final String VIEW_BASE = "/admin/user/";
    
    
    @Autowired
    private UserService userService;

    @Autowired
    private LeagueService leagueService;

    
    
    public UserController() {
        super();
    }
    

    
    
    @RequestMapping("/list")
    public String list(final HttpServletRequest request, final ModelMap model) {
        final List<User> users = this.userService.findAll();
        model.addAttribute("allUsers", users);
        return VIEW_BASE + "list";
    }

    
    
    @RequestMapping("/manage")
    public String manage(
            @RequestParam(value="login",required=false)
            final String login,
            final HttpServletRequest request,
            final ModelMap model) {
        
        final UserBean userBean = new UserBean();
        
        if (login != null) {
            
            final User user = this.userService.find(login);
            
            userBean.setLogin(user.getLogin());
            userBean.setName(user.getName());
            userBean.setEmail(user.getEmail());
            userBean.setAdmin(user.isAdmin());
            userBean.setActive(user.isActive());
            
            final Integer[] leagueIds = new Integer[user.getLeagues().size()];
            int i = 0;
            for (final League league : user.getLeagues()) {
                leagueIds[i++] = league.getId();
            }
            userBean.setLeagueIds(leagueIds);
            
        }
        
        model.addAttribute("user", userBean);
        model.addAttribute("allLeagues", this.leagueService.findAll(RequestContextUtils.getLocale(request)));
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/save")
    public String save(
            final UserBean userBean,
            final BindingResult bindingResult) {

        this.userService.save(
                userBean.getLogin(),
                userBean.getName(),
                userBean.getEmail(),
                userBean.isAdmin(),
                userBean.isActive(),
                (userBean.getLeagueIds() != null? 
                        Arrays.asList(userBean.getLeagueIds()) : new ArrayList<Integer>()));
        
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/resetpassword")
    public String resetPassword(
            @RequestParam(value="login")
            final String login) {

        this.userService.resetPassword(login, true);
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="login")
            final String login) {

        this.userService.delete(login);
        return "redirect:list";
        
    }
    
}
