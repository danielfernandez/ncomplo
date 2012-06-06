package org.eleventhlabs.ncomplo.web.pages;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.eleventhlabs.ncomplo.business.entities.BetFragment;
import org.eleventhlabs.ncomplo.business.entities.BetResult;
import org.eleventhlabs.ncomplo.business.entities.BetResults;
import org.eleventhlabs.ncomplo.business.entities.MatchWinner;
import org.eleventhlabs.ncomplo.business.entities.RoundNew;
import org.eleventhlabs.ncomplo.business.entities.TeamNew;
import org.eleventhlabs.ncomplo.business.services.BetService;
import org.eleventhlabs.ncomplo.business.util.DateUtils;
import org.eleventhlabs.ncomplo.web.application.NComploApplication;

public class BetsDetailPage extends BasePage {

    private static final long serialVersionUID = 5123309121L;


    public BetsDetailPage(final PageParameters pageParameters) {
        
        super();
        
        add(new BookmarkablePageLink<Object>("linkToScoreboard", ScoreboardPage.class));
        
        final String ownerName = pageParameters.getString("ownerName");
        
        if (ownerName == null || ownerName.isEmpty()) {
            throw new IllegalArgumentException("Missing parameter: ownerName");
        }
        
        final BetService betService = NComploApplication.get().getBetService();
        final BetResults betResults = betService.computeBetResults(ownerName);
        
        add(new Label("ownerName", betResults.getOwnerName()));
        add(new Label("groupStagePoints", new Model<Integer>(betResults.getGroupStagePoints())));
        add(new Label("totalPoints", new Model<Integer>(betResults.getTotalPoints())));
        add(new BetResultListView("bet", betResults.getBetResults()));
        
    }
    
    
    private static class BetResultListView extends ListView<BetResult> {

        private static final long serialVersionUID = 500672306834217995L;

        public BetResultListView(String id, List<BetResult> betResults) {
            super(id, betResults);
        }

        @Override
        protected void populateItem(final ListItem<BetResult> item) {
            
            final BetResult betResult = item.getModelObject();
            
            item.add(new Label("round", new Model<RoundNew>(betResult.getRound())));
            
            item.add(new Label("date", new Model<String>(DateUtils.toString(betResult.getDate()))));
            
            final Label teamA = new Label("teamA", new Model<TeamNew>(betResult.getTeamA())); 
            if (betResult.getBetWins().contains(BetFragment.TEAM_A)) {
                teamA.add(new AttributeAppender("class", new Model<String>("betWon"), " "));
            } else if (betResult.getBetLoses().contains(BetFragment.TEAM_A)) {
                teamA.add(new AttributeAppender("class", new Model<String>("betLost"), " "));
            } else if (betResult.getBetFragments().contains(BetFragment.TEAM_A)) {
                teamA.add(new AttributeAppender("class", new Model<String>("bet"), " "));
            }
            item.add(teamA);
            
            final Label teamB = new Label("teamB", new Model<TeamNew>(betResult.getTeamB())); 
            if (betResult.getBetWins().contains(BetFragment.TEAM_B)) {
                teamB.add(new AttributeAppender("class", new Model<String>("betWon"), " "));
            } else if (betResult.getBetLoses().contains(BetFragment.TEAM_B)) {
                teamB.add(new AttributeAppender("class", new Model<String>("betLost"), " "));
            } else if (betResult.getBetFragments().contains(BetFragment.TEAM_B)) {
                teamB.add(new AttributeAppender("class", new Model<String>("bet"), " "));
            }
            item.add(teamB);
            
            final Label score = new Label("score", new Model<String>(betResult.getScore())); 
            if (betResult.getBetWins().contains(BetFragment.SCORE)) {
                score.add(new AttributeAppender("class", new Model<String>("betWon"), " "));
            } else if (betResult.getBetLoses().contains(BetFragment.SCORE)) {
                score.add(new AttributeAppender("class", new Model<String>("betLost"), " "));
            } else if (betResult.getBetFragments().contains(BetFragment.SCORE)) {
                score.add(new AttributeAppender("class", new Model<String>("bet"), " "));
            }
            item.add(score);
            
            final Label realScore = new Label("realScore", new Model<String>(betResult.getRealScore())); 
            item.add(realScore);
            
            final Label matchWinner = new Label("matchWinner", new Model<MatchWinner>(betResult.getMatchWinner())); 
            if (betResult.getBetWins().contains(BetFragment.MATCH_WINNER)) {
                matchWinner.add(new AttributeAppender("class", new Model<String>("betWon"), " "));
            } else if (betResult.getBetLoses().contains(BetFragment.MATCH_WINNER)) {
                matchWinner.add(new AttributeAppender("class", new Model<String>("betLost"), " "));
            } else if (betResult.getBetFragments().contains(BetFragment.MATCH_WINNER)) {
                matchWinner.add(new AttributeAppender("class", new Model<String>("bet"), " "));
            }
            item.add(matchWinner);
            
            final Label realMatchWinner = new Label("realMatchWinner", new Model<MatchWinner>(betResult.getRealMatchWinner())); 
            item.add(realMatchWinner);
            
            final Label points = new Label("points", new Model<Integer>(betResult.getPoints())); 
            item.add(points);
            
            if (betResult.isClosed().booleanValue()) {
                item.add(new AttributeAppender("class", new Model<String>("betClosed"), " "));
            }
        }
        
    }
    
    
}
