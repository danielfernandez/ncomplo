package org.eleventhlabs.ncomplo.web;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.Match;
import org.eleventhlabs.ncomplo.business.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private MatchService matchService;
    
    
    @ModelAttribute("allMatches")
    public List<Match> allMatches() {
        return this.matchService.findAllMatchesOrderByDate();
    }
    
    
    @RequestMapping("/test")
    public String test() {
        return "testTemplate";
    }
}
