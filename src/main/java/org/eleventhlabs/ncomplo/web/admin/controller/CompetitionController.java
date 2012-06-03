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
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;
    
    
    @ModelAttribute("allCompetitions")
    public List<Competition> allCompetitions(final HttpServletRequest request) {
        System.out.println("LOCALE IS: " + RequestContextUtils.getLocale(request));
        return this.competitionService.findAllOrderByName(RequestContextUtils.getLocale(request));
    }
    
    
    @RequestMapping("/admin/competition/list")
    public String list() {
        return "admin/competition/list";
    }

    
    @RequestMapping("/admin/competition/manage")
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
        
        return "admin/competition/manage";
        
    }

    
    @RequestMapping("/admin/competition/manage.do")
    public String doManage(
            final CompetitionBean competitionBean,
            final BindingResult bindingResult) {
        
        if (competitionBean.getId() == null) {
            this.competitionService.addCompetition(
                    competitionBean.getNames(), competitionBean.isActive());
        } else {
            this.competitionService.modifyCompetition(
                    competitionBean.getId(), competitionBean.getNames(),
                    competitionBean.isActive());
        }
        
        return "redirect:/admin/competition/list";
        
    }
    
    
}
