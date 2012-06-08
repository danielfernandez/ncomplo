package org.eleventhlabs.ncomplo.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.services.BetService;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;
import org.eleventhlabs.ncomplo.exceptions.InternalErrorException;
import org.eleventhlabs.ncomplo.web.beans.LeagueSelectorBean;
import org.eleventhlabs.ncomplo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class ScoreboardController {
    
    
    @Autowired
    private UserService userService;

    @Autowired
    private BetService betService;
    

    
    
    public ScoreboardController() {
        super();
    }
    

    
    
    
    
    @RequestMapping({"/","/scoreboard"})
    public String scoreboard(
            final HttpServletRequest request) {
        
        final Locale locale = RequestContextUtils.getLocale(request);
        
        final String login = SessionUtil.getAuthenticatedUser(request);
        final User user = this.userService.find(login);
        
        final List<League> userLeagues = new ArrayList<League>(user.getLeagues());
        final List<League> activeUserLeagues = new ArrayList<League>();
        for (final League league : userLeagues) {
            if (league.isActive()) {
                activeUserLeagues.add(league);
            }
        }
        Collections.sort(activeUserLeagues, new I18nNamedEntityComparator(locale));
        
        if (activeUserLeagues.size() == 0) {
            throw new InternalErrorException("No active leagues for user \"" +  login + "\"");
        }

        return "redirect:/scoreboard/" + activeUserLeagues.get(0).getId();
        
    }
    


    
    
    @RequestMapping({"/scoreboard/{leagueId}"})
    public String scoreboard(
            @PathVariable("leagueId") Integer leagueId,
            final HttpServletRequest request,
            final ModelMap model) {
        
        final Locale locale = RequestContextUtils.getLocale(request);
        
        final String login = SessionUtil.getAuthenticatedUser(request);
        final User user = this.userService.find(login);
        
        final List<League> userLeagues = new ArrayList<League>(user.getLeagues());
        final List<League> activeUserLeagues = new ArrayList<League>();
        for (final League league : userLeagues) {
            if (league.isActive()) {
                activeUserLeagues.add(league);
            }
        }
        Collections.sort(activeUserLeagues, new I18nNamedEntityComparator(locale));

        final LeagueSelectorBean leagueSelectorBean = new LeagueSelectorBean();
        leagueSelectorBean.setLeagueId(leagueId);
        
        
        model.addAttribute("user", user);
        model.addAttribute("leagueSelector", leagueSelectorBean);
        model.addAttribute("allLeagues", activeUserLeagues);
        model.addAttribute("showLeagueSelector", Boolean.valueOf(activeUserLeagues.size() > 1));
        
        return "scoreboard";
        
    }
    
    
    
    

    @RequestMapping({"/selectScoreboard"})
    public String selectScoreboard(final LeagueSelectorBean leagueSelectorBean) {
        final Integer leagueId = leagueSelectorBean.getLeagueId();
        return "redirect:/scoreboard/" + leagueId;
    }
    
}
