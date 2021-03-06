package org.eleventhlabs.ncomplo.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.Bet;
import org.eleventhlabs.ncomplo.business.entities.Bet.BetComparator;
import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.Round;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.services.BetService;
import org.eleventhlabs.ncomplo.business.services.LeagueService;
import org.eleventhlabs.ncomplo.business.services.ScoreboardService;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.views.ScoreboardEntry;
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

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private ScoreboardService scoreboardService;
    

    
    
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
    


    
    
    @RequestMapping("/scoreboard/{leagueId}")
    public String scoreboardByLeague(
            @PathVariable("leagueId") final Integer leagueId,
            final HttpServletRequest request,
            final ModelMap model) {
        
        return computeScoreboard(leagueId, null, request, model);
        
    }
    
    
    

    @RequestMapping("/scoreboard/{leagueId}/{roundId}")
    public String scoreboardByLeagueAndRound(
            @PathVariable("leagueId") final Integer leagueId,
            @PathVariable("roundId") final Integer roundId,
            final HttpServletRequest request,
            final ModelMap model) {
        
        return computeScoreboard(leagueId, roundId, request, model);
        
    }
    
    
    

    private String computeScoreboard(
            final Integer leagueId,
            final Integer roundId,
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
        leagueSelectorBean.setRoundId(roundId);

        final League league = this.leagueService.find(leagueId);
        
        final List<Round> rounds = new ArrayList<Round>(league.getCompetition().getRounds());
        Collections.sort(rounds);
        
        final List<ScoreboardEntry> scoreboardEntries =
                this.scoreboardService.computeScoreboard(leagueId, roundId, locale);
       
        model.addAttribute("scoreboardEntries", scoreboardEntries);
        model.addAttribute("user", user);
        model.addAttribute("league", league);
        model.addAttribute("leagueSelector", leagueSelectorBean);
        model.addAttribute("allLeagues", activeUserLeagues);
        model.addAttribute("allRounds", rounds);
        model.addAttribute("showLeagueSelector", Boolean.valueOf(activeUserLeagues.size() > 1));
        
        return "scoreboard";
        
    }
    
    
    

    @RequestMapping({"/selectScoreboard"})
    public String selectScoreboard(final LeagueSelectorBean leagueSelectorBean) {
        final Integer leagueId = leagueSelectorBean.getLeagueId();
        final Integer roundId = leagueSelectorBean.getRoundId();
        if (roundId != null) {
            return "redirect:/scoreboard/" + leagueId + "/" + roundId;
        }
        return "redirect:/scoreboard/" + leagueId;
    }

    
    

    
    @RequestMapping({"/bets/{leagueId}/{login}"})
    public String bets(
            @PathVariable("leagueId") Integer leagueId,
            @PathVariable("login") String login,
            final HttpServletRequest request,
            final ModelMap model) {
        
        final Locale locale = RequestContextUtils.getLocale(request);
        
        final User user = this.userService.find(login);
        final List<Bet> betsForUser =
                this.betService.findByLeagueIdAndUserLogin(leagueId, login, locale);
        Collections.sort(betsForUser, new BetComparator(locale));
        
        final League league = this.leagueService.find(leagueId);
        
        final List<ScoreboardEntry> scoreboardEntries =
                this.scoreboardService.computeScoreboard(leagueId, null, locale);
       
        model.addAttribute("scoreboardEntries", scoreboardEntries);
        
        model.addAttribute("user", user);
        model.addAttribute("league", league);
        model.addAttribute("allBets", betsForUser);
        
        return "bets";
        
    }
    
    
    
}
