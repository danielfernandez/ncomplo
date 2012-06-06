package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Round;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.business.services.RoundService;
import org.eleventhlabs.ncomplo.web.admin.beans.LangBean;
import org.eleventhlabs.ncomplo.web.admin.beans.RoundBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/competition/{competitionId}/round")
public class RoundController {

    private static final String VIEW_BASE = "/admin/competition/round/";
    
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private RoundService roundService;

    
    
    
    public RoundController() {
        super();
    }
    



    
    
    @RequestMapping("/list")
    public String list(
            @PathVariable("competitionId") final Integer competitionId, 
            final ModelMap model) {
        
        final List<Round> rounds = this.roundService.findAllOrderByName(competitionId);
        
        model.addAttribute("allRounds", rounds);
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

        final RoundBean roundBean = new RoundBean();
        
        if (id != null) {
            final Round round = this.roundService.find(id);
            roundBean.setId(round.getId());
            roundBean.setName(round.getName());
            roundBean.getNamesByLang().clear();
            roundBean.getNamesByLang().addAll(LangBean.listFromMap(round.getNamesByLang()));
            roundBean.setOrder(round.getOrder());
        }
        
        model.addAttribute("round", roundBean);
        model.addAttribute("competition", this.competitionService.find(competitionId));
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/save")
    public String save(
            final RoundBean roundBean,
            final BindingResult bindingResult,
            @PathVariable("competitionId")
            final Integer competitionId) {

        this.roundService.save(
                roundBean.getId(),
                competitionId,
                roundBean.getName(),
                LangBean.mapFromList(roundBean.getNamesByLang()),
                roundBean.getOrder());
        
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.roundService.delete(id);
        return "redirect:list";
        
    }
    
    
}
