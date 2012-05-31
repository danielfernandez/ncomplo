package org.eleventhlabs.ncomplo.web.pages;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.eleventhlabs.ncomplo.business.entities.Scoreboard;
import org.eleventhlabs.ncomplo.business.entities.Scoreboard.ScoreboardPosition;
import org.eleventhlabs.ncomplo.web.application.NComploApplication;

public class ScoreboardPage extends BasePage {

    private static final long serialVersionUID = 5123309121L;


    public ScoreboardPage(final PageParameters pageParameters) {
        
        super();
        
        final Scoreboard scoreboard = 
                NComploApplication.get().getBetService().computeScoreboard();
        
        add(new ScoreBoardListView("totalScore", scoreboard.getTotalScores()));
        add(new ScoreBoardListView("groupStageScore", scoreboard.getGroupStageScores()));
        
    }
    
    
    private static class ScoreBoardListView extends ListView<ScoreboardPosition> {

        private static final long serialVersionUID = 1993011615071486309L;

        public ScoreBoardListView(final String id, final List<ScoreboardPosition> list) {
            super(id, list);
        }

        @Override
        protected void populateItem(final ListItem<ScoreboardPosition> item) {
            
            final ScoreboardPosition scoreboardPosition = item.getModelObject();
            
            
            boolean showPositionNumber = true;
            if (item.getIndex() > 0) {
                final Integer lastPosition = getModelObject().get(item.getIndex() - 1).getNumber();
                if (scoreboardPosition.getNumber().equals(lastPosition)) {
                    showPositionNumber = false;
                }
            }
            
            final Label position =
                (showPositionNumber?
                        new Label("position", new Model<Integer>(scoreboardPosition.getNumber())) :
                        new Label("position", new Model<String>()));
            item.add(position);
            
            final Label name = 
                new Label("ownerName", new Model<String>(scoreboardPosition.getOwnerName()));
            item.add(name);
            
            final Label points = 
                new Label("points", new Model<Integer>(scoreboardPosition.getPoints()));
            item.add(points);
            
            final PageParameters pageParameters = new PageParameters();
            pageParameters.put("ownerName", scoreboardPosition.getOwnerName());
            
            item.add(new BookmarkablePageLink<Object>("detailsLink", BetsDetailPage.class, pageParameters));
            
            item.add(new AttributeAppender("class", new Model<String>("pos" + scoreboardPosition.getNumber()), " "));
            
        }
        
    }
    
    
}
