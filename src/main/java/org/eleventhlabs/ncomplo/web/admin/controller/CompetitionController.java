package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.web.admin.beans.CompetitionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    
    @ModelAttribute("allCompetitions")
    public List<Competition> allCompetitions(final HttpServletRequest request) {
        return this.competitionService.findAllOrderByName(RequestContextUtils.getLocale(request));
    }

    
    
    @RequestMapping("/list")
    public String list() {
        return VIEW_BASE + "list";
    }

    
    
    @RequestMapping("/manage")
    public String manage(
            @ModelAttribute("competition") 
            final CompetitionBean competitionBean,
            @RequestParam(value="id",required=false)
            final Integer id) {
        
        if (id != null) {
            final Competition competition = this.competitionService.find(id);
            competitionBean.getNames().clear();
            competitionBean.getNames().putAll(competition.getNames());
            competitionBean.setActive(competition.isActive());
        }
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/manage.do")
    public String doManage(
            final CompetitionBean competitionBean,
            final BindingResult bindingResult) {
        
        if (competitionBean.getId() == null) {
            this.competitionService.add(
                    competitionBean.getNames(), competitionBean.isActive());
        } else {
            this.competitionService.modify(
                    competitionBean.getId(), competitionBean.getNames(),
                    competitionBean.isActive());
        }
        
        return "redirect:" + VIEW_BASE + "list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.competitionService.delete(id);
        return "redirect:" + VIEW_BASE + "list";
        
    }
    
    
}
