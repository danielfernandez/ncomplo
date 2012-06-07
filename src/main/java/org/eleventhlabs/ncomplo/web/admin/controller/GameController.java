package org.eleventhlabs.ncomplo.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.Game;
import org.eleventhlabs.ncomplo.business.services.BetTypeService;
import org.eleventhlabs.ncomplo.business.services.CompetitionService;
import org.eleventhlabs.ncomplo.business.services.GameService;
import org.eleventhlabs.ncomplo.business.services.RoundService;
import org.eleventhlabs.ncomplo.business.services.GameSideService;
import org.eleventhlabs.ncomplo.business.util.I18nUtils;
import org.eleventhlabs.ncomplo.web.admin.beans.LangBean;
import org.eleventhlabs.ncomplo.web.admin.beans.GameBean;
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
@RequestMapping("/admin/competition/{competitionId}/game")
public class GameController {

    private static final String VIEW_BASE = "/admin/competition/game/";
    
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private GameService gameService;
    
    @Autowired
    private RoundService roundService;
    
    @Autowired
    private GameSideService gameSideService;
    
    @Autowired
    private BetTypeService betTypeService;

    
    
    
    public GameController() {
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
        
        final List<Game> games =
                this.gameService.findAll(competitionId,RequestContextUtils.getLocale(request));
        
        model.addAttribute("allGames", games);
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

        final GameBean gameBean = new GameBean();
        
        if (id != null) {
            final Game game = this.gameService.find(id);
            gameBean.setId(game.getId());
            gameBean.setName(game.getName());
            gameBean.getNamesByLang().clear();
            gameBean.getNamesByLang().addAll(LangBean.listFromMap(game.getNamesByLang()));
            gameBean.setDate(game.getDate());
            gameBean.setDefaultBetTypeId(game.getDefaultBetType().getId());
            gameBean.setRoundId(game.getRound().getId());
            gameBean.setGameSideAId(
                    game.getGameSideA() == null?
                            null : game.getGameSideA().getId());
            gameBean.setGameSideBId(
                    game.getGameSideB() == null?
                            null : game.getGameSideB().getId());
            gameBean.setScoreA(game.getScoreA());
            gameBean.setScoreB(game.getScoreB());
        }
        
        model.addAttribute("game", gameBean);
        model.addAttribute("competition", this.competitionService.find(competitionId));
        
        final Locale locale = RequestContextUtils.getLocale(request);
        model.addAttribute("allRounds", this.roundService.findAll(competitionId));
        model.addAttribute("allBetTypes", this.betTypeService.findAllOrderByName(competitionId, locale));
        model.addAttribute("allGameSides", this.gameSideService.findAll(competitionId, locale));
        
        return VIEW_BASE + "manage";
        
    }

    
    
    @RequestMapping("/save")
    public String save(
            final GameBean gameBean,
            final BindingResult bindingResult,
            @PathVariable("competitionId")
            final Integer competitionId) {

        this.gameService.save(
                gameBean.getId(),
                competitionId,
                gameBean.getDate(),
                gameBean.getName(),
                LangBean.mapFromList(gameBean.getNamesByLang()),
                gameBean.getDefaultBetTypeId(),
                gameBean.getRoundId(),
                gameBean.getGameSideAId(),
                gameBean.getGameSideBId(),
                gameBean.getScoreA(),
                gameBean.getScoreB());
        
        return "redirect:list";
        
    }

    
    
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value="id")
            final Integer id) {

        this.gameService.delete(id);
        return "redirect:list";
        
    }
    
    
}
