package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.eleventhlabs.ncomplo.business.entities.Bet;
import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Game;
import org.eleventhlabs.ncomplo.business.entities.GameSide;
import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.LeagueGame;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.entities.User.UserComparator;
import org.eleventhlabs.ncomplo.business.services.BetService;
import org.eleventhlabs.ncomplo.business.services.LeagueService;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.eleventhlabs.ncomplo.business.util.DatedAndNamedEntityComparator;
import org.eleventhlabs.ncomplo.business.util.I18nNamedEntityComparator;
import org.eleventhlabs.ncomplo.web.admin.beans.BetBean;
import org.eleventhlabs.ncomplo.web.admin.beans.ParticipationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/league/{leagueId}/participants")
public class ParticipantBetsController {

    private static final String VIEW_BASE = "/admin/league/participants/";
    
    
    @Autowired
    private LeagueService leagueService;

    @Autowired
    private UserService userService;

    @Autowired
    private BetService betService;
    
    
    
    
    
    public ParticipantBetsController() {
        super();
    }
    



    
    
    @RequestMapping("/list")
    public String list(
            @PathVariable("leagueId") final Integer leagueId,
            final HttpServletRequest request,
            final ModelMap model) {
        
        final Locale locale = RequestContextUtils.getLocale(request);
        
        final List<User> participants =
                new ArrayList<User>(this.leagueService.find(leagueId).getParticipants());
        Collections.sort(participants, new UserComparator(locale));
        
        model.addAttribute("allParticipants", participants);
        model.addAttribute("league", this.leagueService.find(leagueId));
        
        return VIEW_BASE + "list";
        
    }


    
    
    @RequestMapping("/manage")
    public String manage(
            @RequestParam(value="leagueId",required=true)
            final Integer leagueId,
            @RequestParam(value="login",required=true)
            final String login,
            final ModelMap model,
            final HttpServletRequest request) {

        final Locale locale = RequestContextUtils.getLocale(request);
        
        final League league = this.leagueService.find(leagueId);
        final Competition competition = league.getCompetition();
        final User participant = this.userService.find(login);
        
        final List<GameSide> competitionGameSides =
                new ArrayList<GameSide>(competition.getGameSides());
        Collections.sort(competitionGameSides, new I18nNamedEntityComparator(locale));
        
        final List<Bet> bets =
                this.betService.findByLeagueIdAndUserLogin(leagueId, login, locale);
        
        final ParticipationBean participationBean = new ParticipationBean();
        participationBean.setLeagueId(leagueId);
        participationBean.setLogin(login);

        final List<Game> allGames = new ArrayList<Game>();
        for (final LeagueGame leagueGame : league.getLeagueGames().values()) {
            
            final BetBean betBean = new BetBean();
            final Game game = leagueGame.getGame();
            betBean.setBetTypeId(leagueGame.getBetType().getId());
            betBean.setGameId(game.getId());
            if (game.getGameSideA() != null) {
                betBean.setGameSideAId(game.getGameSideA().getId());
            }
            if (game.getGameSideB() != null) {
                betBean.setGameSideBId(game.getGameSideB().getId());
            }
            
            participationBean.getBetsByGame().put(game.getId(), betBean);
            
            allGames.add(game);
            
        }
        
        Collections.sort(allGames, new DatedAndNamedEntityComparator(locale));
        
        if (bets != null && bets.size() > 0) {

            for (final Bet bet : bets) {
                
                final Game game = bet.getGame();
                final BetBean betBean = participationBean.getBetsByGame().get(game.getId());
                
                betBean.setId(bet.getId());
                if (bet.getGameSideA() != null) {
                    betBean.setGameSideAId(bet.getGameSideA().getId());
                }
                if (bet.getGameSideB() != null) {
                    betBean.setGameSideBId(bet.getGameSideB().getId());
                }
                betBean.setScoreA(bet.getScoreA());
                betBean.setScoreB(bet.getScoreB());
                
            }
            
        }
        
        model.addAttribute("participation", participationBean);
        model.addAttribute("league", league);
        model.addAttribute("competition", competition);
        model.addAttribute("participant", participant);
        model.addAttribute("allGames", allGames);
        model.addAttribute("allGameSides", competitionGameSides);
        model.addAttribute("allBets", bets);
        
        return VIEW_BASE + "manage";
        
    }



    
    @RequestMapping("/save")
    public String save(
            final ParticipationBean participationBean,
            final BindingResult bindingResult) {

        
        for (final BetBean betBean : participationBean.getBetsByGame().values()) {
            
            this.betService.save(
                    betBean.getId(),
                    participationBean.getLeagueId(),
                    participationBean.getLogin(),
                    betBean.getGameId(),
                    betBean.getGameSideAId(),
                    betBean.getGameSideBId(),
                    betBean.getScoreA(),
                    betBean.getScoreB());
            
        }
        
        return "redirect:list";
        
    }
    
    
}
