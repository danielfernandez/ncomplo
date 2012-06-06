package org.eleventhlabs.ncomplo.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.Match;
import org.eleventhlabs.ncomplo.business.services.BetTypeService;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.business.services.MatchService;
import org.eleventhlabs.ncomplo.business.services.RoundService;
import org.eleventhlabs.ncomplo.business.services.TeamService;
import org.eleventhlabs.ncomplo.business.util.I18nUtils;
import org.eleventhlabs.ncomplo.web.admin.beans.LangBean;
import org.eleventhlabs.ncomplo.web.admin.beans.MatchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/competition/{competitionId}/match")
public class MatchController {

    private static final String VIEW_BASE = "/admin/competition/match/";
    
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private MatchService matchService;
    
    @Autowired
    private RoundService roundService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private BetTypeService betTypeService;

    
    
    
    public MatchController() {
        super();
    }
    

    @InitBinder
    public void initDateBinder(final WebDataBinder dataBinder) {
        final SimpleDateFormat sdf = new SimpleDateFormat(I18nUtils.ISO_DATE_FORMAT);
        sdf.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
    
    
    @RequestMapping("/list")
    public String list(
            @PathVariable("competitionId") final Integer competitionId, 
            final HttpServletRequest request, 
            final ModelMap model) {
        
        final List<Match> matches =
                this.matchService.findAllOrderByName(competitionId,RequestContextUtils.getLocale(request));
        
        model.addAttribute("allMatches", matches);
        model.addAttribute("competition", this.competitionService.find(competitionId));
        
        return VIEW_BASE + "list";
        
    }

    
    
    @RequestMapping("/manage")
    public String manage(
            @RequestParam(value="id",required=false)
            final Integer id,
            @PathVariable("competitionId")
            final Integer competitionId,
            final ModelMap model,
            final HttpServletRequest request) {

        final MatchBean matchBean = new MatchBean();
        
        if (id != null) {
            final Match match = this.matchService.find(id);
            matchBean.setId(match.getId());
            matchBean.setName(match.getName());
            matchBean.getNamesByLang().clear();
            matchBean.getNamesByLang().addAll(LangBean.listFromMap(match.getNamesByLang()));
            matchBean.setDate(match.getDate());
            matchBean.setDefaultBetTypeId(match.getDefaultBetType().getId());
            matchBean.setRoundId(match.getRound().getId());
            matchBean.setTeamAId(
                    match.getTeamA() == null?
                            null : match.getTeamA().getId());
            matchBean.setTeamBId(
                    match.getTeamB() == null?
                            null : match.getTeamB().getId());
            matchBean.setScoreA(match.getScoreA());
            matchBean.setScoreB(match.getScoreB());
        }
        
        model.addAttribute("match", matchBean);
        model.addAttribute("competition", this.competitionService.find(competitionId));
        
        final Locale locale = RequestContextUtils.getLocale(request);
        model.addAttribute("allRounds", this.roundService.findAllOrderByName(competitionId));
        model.addAttribute("allBetTypes", this.betTypeService.findAllOrderByName(competitionId, locale));
        model.addAttribute("allTeams", this.teamService.findAllOrderByName(competitionId, locale));
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/save")
    public String save(
            final MatchBean matchBean,
            final BindingResult bindingResult,
            @PathVariable("competitionId")
            final Integer competitionId) {

        this.matchService.save(
                matchBean.getId(),
                competitionId,
                matchBean.getDate(),
                matchBean.getName(),
                LangBean.mapFromList(matchBean.getNamesByLang()),
                matchBean.getDefaultBetTypeId(),
                matchBean.getRoundId(),
                matchBean.getTeamAId(),
                matchBean.getTeamBId(),
                matchBean.getScoreA(),
                matchBean.getScoreB());
        
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.matchService.delete(id);
        return "redirect:list";
        
    }
    
    
}
