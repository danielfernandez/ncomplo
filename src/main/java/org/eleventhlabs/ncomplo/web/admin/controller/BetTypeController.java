package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.services.BetTypeService;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.web.admin.beans.BetTypeBean;
import org.eleventhlabs.ncomplo.web.admin.beans.LangBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/competition/{competitionId}/bettype")
public class BetTypeController {

    private static final String VIEW_BASE = "/admin/competition/bettype/";
    
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private BetTypeService betTypeService;

    
    
    
    public BetTypeController() {
        super();
    }
    



    
    
    @RequestMapping("/list")
    public String list(
            @PathVariable("competitionId") final Integer competitionId, 
            final HttpServletRequest request, 
            final ModelMap model) {
        
        final List<BetType> betTypes =
                this.betTypeService.findAllOrderByName(competitionId,RequestContextUtils.getLocale(request));
        
        model.addAttribute("allBetTypes", betTypes);
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

        final BetTypeBean betTypeBean = new BetTypeBean();
        
        if (id != null) {
            final BetType betType = this.betTypeService.find(id);
            betTypeBean.setId(betType.getId());
            betTypeBean.setName(betType.getName());
            betTypeBean.getNamesByLang().clear();
            betTypeBean.getNamesByLang().addAll(LangBean.listFromMap(betType.getNamesByLang()));
            betTypeBean.setSpec(betType.getSpec());
            betTypeBean.setSidesMatter(betType.isSidesMatter());
            betTypeBean.setScoreMatter(betType.isScoreMatter());
            betTypeBean.setResultMatter(betType.isResultMatter());
        }
        
        model.addAttribute("betType", betTypeBean);
        model.addAttribute("competition", this.competitionService.find(competitionId));
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/save")
    public String save(
            final BetTypeBean betTypeBean,
            final BindingResult bindingResult,
            @PathVariable("competitionId")
            final Integer competitionId) {

        this.betTypeService.save(
                betTypeBean.getId(),
                competitionId,
                betTypeBean.getName(),
                LangBean.mapFromList(betTypeBean.getNamesByLang()),
                betTypeBean.getSpec(),
                betTypeBean.isSidesMatter(),
                betTypeBean.isScoreMatter(),
                betTypeBean.isResultMatter());
        
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.betTypeService.delete(id);
        return "redirect:list";
        
    }
    
    
}
