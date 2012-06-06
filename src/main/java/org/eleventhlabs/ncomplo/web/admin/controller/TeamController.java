package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.Team;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.business.services.TeamService;
import org.eleventhlabs.ncomplo.web.admin.beans.LangBean;
import org.eleventhlabs.ncomplo.web.admin.beans.TeamBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/competition/{competitionId}/team")
public class TeamController {

    private static final String VIEW_BASE = "/admin/competition/team/";
    
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private TeamService teamService;

    
    
    
    public TeamController() {
        super();
    }
    



    
    
    @RequestMapping("/list")
    public String list(
            @PathVariable("competitionId") final Integer competitionId, 
            final HttpServletRequest request, 
            final ModelMap model) {
        
        final List<Team> teams =
                this.teamService.findAllOrderByName(competitionId,RequestContextUtils.getLocale(request));
        
        model.addAttribute("allTeams", teams);
        model.addAttribute("competition", this.competitionService.find(competitionId));
        
        return VIEW_BASE + "list";
        
    }

    
    
    @RequestMapping("/manage")
    public String manage(
            @RequestParam(value="id",required=false)
            final Integer id,
            @PathVariable("competitionId")
            final Integer competitionId,
            final ModelMap model) {

        final TeamBean teamBean = new TeamBean();
        
        if (id != null) {
            final Team team = this.teamService.find(id);
            teamBean.setId(team.getId());
            teamBean.setName(team.getName());
            teamBean.getNamesByLang().clear();
            teamBean.getNamesByLang().addAll(LangBean.listFromMap(team.getNamesByLang()));
        }
        
        model.addAttribute("team", teamBean);
        model.addAttribute("competition", this.competitionService.find(competitionId));
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/save")
    public String save(
            final TeamBean teamBean,
            final BindingResult bindingResult,
            @PathVariable("competitionId")
            final Integer competitionId) {

        this.teamService.save(
                teamBean.getId(),
                competitionId,
                teamBean.getName(),
                LangBean.mapFromList(teamBean.getNamesByLang()));
        
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.teamService.delete(id);
        return "redirect:list";
        
    }
    
    
}
