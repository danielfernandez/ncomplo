package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Game;
import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.LeagueGame;
import org.eleventhlabs.ncomplo.business.services.BetTypeService;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.business.services.LeagueService;
import org.eleventhlabs.ncomplo.business.util.DatedAndNamedEntityComparator;
import org.eleventhlabs.ncomplo.exceptions.InternalErrorException;
import org.eleventhlabs.ncomplo.web.admin.beans.LangBean;
import org.eleventhlabs.ncomplo.web.admin.beans.LeagueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/league")
public class LeagueController {

    private static final String VIEW_BASE = "/admin/league/";
    
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private LeagueService leagueService;
    
    @Autowired
    private BetTypeService betTypeService;

    
    
    
    public LeagueController() {
        super();
    }
    
    
    
    
    
    @RequestMapping("/list")
    public String list(
            final HttpServletRequest request, 
            final ModelMap model) {
        
        final List<League> leagues =
                this.leagueService.findAll(RequestContextUtils.getLocale(request));
        final List<Competition> competitions =
                this.competitionService.findAll(RequestContextUtils.getLocale(request));
        
        model.addAttribute("allLeagues", leagues);
        model.addAttribute("allCompetitions", competitions);
        
        return VIEW_BASE + "list";
        
    }

    
    
    @RequestMapping("/manage")
    public String manage(
            @RequestParam(value="id",required=false)
            final Integer id,
            @RequestParam(value="competitionId",required=false)
            final Integer competitionId,
            final ModelMap model,
            final HttpServletRequest request) {

        final Locale locale = RequestContextUtils.getLocale(request);
        
        final League league =
                (id == null? null : this.leagueService.find(id));
        final Integer leagueCompetitionId =
                (league == null? competitionId : league.getCompetition().getId());
        if (leagueCompetitionId == null) {
            throw new InternalErrorException(
                    "\"competitionId\" is mandatory if no \"id\" is specified.");
        }
        
        final Competition competition =
                this.competitionService.find(leagueCompetitionId);
                
        final List<Game> allGamesForCompetition = new ArrayList<Game>(competition.getGames());
        Collections.sort(allGamesForCompetition, new DatedAndNamedEntityComparator(locale));
                
        
        final LeagueBean leagueBean = new LeagueBean();
        leagueBean.setCompetitionId(leagueCompetitionId);
        
        for (final Game game : allGamesForCompetition) {
            // Initialize default values for game bet types
            final BetType defaultBetType = game.getDefaultBetType();
            leagueBean.getBetTypesByGame().put(game.getId(), defaultBetType.getId());
        }
        
        if (league != null) {
            
            leagueBean.setId(league.getId());
            leagueBean.setName(league.getName());
            leagueBean.getNamesByLang().clear();
            leagueBean.getNamesByLang().addAll(LangBean.listFromMap(league.getNamesByLang()));
            leagueBean.setAdminEmail(league.getAdminEmail());
            leagueBean.setActive(league.isActive());
            
            for (final LeagueGame leagueGame : league.getLeagueGames().values()) {
                leagueBean.getBetTypesByGame().put(leagueGame.getGame().getId(), leagueGame.getBetType().getId());                
            }
            
        }
        
        model.addAttribute("league", leagueBean);
        model.addAttribute("competition", competition);
        model.addAttribute("allGames", allGamesForCompetition);
        model.addAttribute("allBetTypes", this.betTypeService.findAllOrderByName(leagueCompetitionId, locale));
        
        return VIEW_BASE + "manage";
        
    }

    
    
    
    @RequestMapping("/save")
    public String save(
            final LeagueBean leagueBean,
            final BindingResult bindingResult) {

        this.leagueService.save(
                leagueBean.getId(),
                leagueBean.getCompetitionId(),
                leagueBean.getName(),
                LangBean.mapFromList(leagueBean.getNamesByLang()),
                leagueBean.getAdminEmail(),
                leagueBean.isActive(),
                leagueBean.getBetTypesByGame());
        
        return "redirect:list";
        
    }


    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.leagueService.delete(id);
        return "redirect:list";
        
    }
    

    
    
    @RequestMapping("/recompute")
    public String manage(
            @RequestParam(value="id",required=true)
            final Integer leagueId) {
    
        this.leagueService.recomputeScores(leagueId);
        
        return "redirect:list";
        
    }
}
