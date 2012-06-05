package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.web.admin.beans.CompetitionBean;
import org.eleventhlabs.ncomplo.web.admin.beans.LangBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/competition")
public class CompetitionController {

    private static final String VIEW_BASE = "/admin/competition/";
    
    
    @Autowired
    private CompetitionService competitionService;

    
    
    public CompetitionController() {
        super();
    }
    

    
    
    @RequestMapping("/list")
    public String list(final HttpServletRequest request, final ModelMap model) {
        final List<Competition> competitions =
                this.competitionService.findAllOrderByName(RequestContextUtils.getLocale(request));
        model.addAttribute("allCompetitions", competitions);
        return VIEW_BASE + "list";
    }

    
    
    @RequestMapping("/manage")
    public String manage(
            @RequestParam(value="id",required=false)
            final Integer id,
            final ModelMap model) {
        
        final CompetitionBean competitionBean = new CompetitionBean();
        
        if (id != null) {
            
            final Competition competition = this.competitionService.find(id);
            
            competitionBean.setId(competition.getId());
            competitionBean.setName(competition.getName());
            competitionBean.setActive(competition.isActive());
            
            competitionBean.getNamesByLang().clear();
            competitionBean.getNamesByLang().addAll(LangBean.listFromMap(competition.getNamesByLang()));
            
        }
        
        model.addAttribute("competition", competitionBean);
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/save")
    public String save(
            final CompetitionBean competitionBean,
            final BindingResult bindingResult) {

        this.competitionService.save(
                competitionBean.getId(),
                competitionBean.getName(),
                LangBean.mapFromList(competitionBean.getNamesByLang()),
                competitionBean.isActive());
        
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.competitionService.delete(id);
        return "redirect:list";
        
    }
    
    
}
