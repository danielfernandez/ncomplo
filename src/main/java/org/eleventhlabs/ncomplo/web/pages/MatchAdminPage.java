package org.eleventhlabs.ncomplo.web.pages;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.NumberValidator.MinimumValidator;
import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.entities.Match;
import org.eleventhlabs.ncomplo.business.entities.Round;
import org.eleventhlabs.ncomplo.business.entities.Team;
import org.eleventhlabs.ncomplo.business.services.MatchService;
import org.eleventhlabs.ncomplo.business.util.DateUtils;
import org.eleventhlabs.ncomplo.exceptions.InternalErrorException;
import org.eleventhlabs.ncomplo.web.application.NComploApplication;
import org.eleventhlabs.ncomplo.web.utils.JavascriptEventConfirmation;

public class MatchAdminPage extends BaseAdminPage {

    private static final long serialVersionUID = 512309121L;

    
    public MatchAdminPage() {
        this(null);
    }
    
    public MatchAdminPage(final Integer matchId) {
        super();
        add(new BookmarkablePageLink<Object>("linkBetAdmin", BetAdminPage.class));
        add(new FeedbackPanel("feedbackPanel"));
        add(new MatchListView("match"));
        add(new MatchForm("matchForm", matchId));
    }
    
    
    
    
    private static class MatchListModel extends LoadableDetachableModel<List<Match>> {

        private static final long serialVersionUID = 76824L;

        public MatchListModel() {
            super();
        }
        
        @Override
        protected List<Match> load() {
            return NComploApplication.get().getMatchService().findAllMatchesOrderByDate();
        }
        
    }
    
    
    
    private static class MatchListView extends ListView<Match> {

        private static final long serialVersionUID = 5766052938465348690L;

        public MatchListView(String id) {
            super(id, new MatchListModel());
        }

        @Override
        protected void populateItem(final ListItem<Match> item) {

            final Match match = item.getModelObject();
            final Integer matchId = match.getId();
            
            item.add(new Label("round", new Model<Round>(match.getRound())));
            item.add(new Label("date", DateUtils.toString(match.getDate())));
            item.add(new Label("name", match.getName()));
            item.add(new Label("betType", new Model<BetType>(match.getBetType())));
            
            String teams = "";
            if (match.isTeamsDefined()) {
                teams = match.getTeamA() + " - " + match.getTeamB();
            } else {
                if (match.getTeamA() != null) {
                    teams = match.getTeamA() + " - ?";
                } else if (match.getTeamB() != null) {
                    teams = "? - " + match.getTeamB().toString();
                }
            }
            item.add(new Label("teams", teams));
            
            String score = "";
            if (match.isScoreDefined()) {
                score = match.getScoreA() + " - " + match.getScoreB();
            }
            item.add(new Label("score", score));

            item.add(new Link<Object>("edit") {

                private static final long serialVersionUID = 8034161985197309644L;

                @Override
                public void onClick() {
                    setResponsePage(new MatchAdminPage(matchId));
                }
                
            });

            final Link<Object> removeLink = new 
                Link<Object>("remove") {
    
                    private static final long serialVersionUID = 2034111985197309644L;
    
                    @Override
                    public void onClick() {
                        NComploApplication.get().getMatchService().deleteMatch(matchId);
                    }
                    
                };
            removeLink.add(new JavascriptEventConfirmation("onclick", "\u00BFSeguro que desea borrar este partido?"));
            item.add(removeLink);
        }
        
    }
    
    
    
    
    private static class MatchForm extends Form<Object> {

        private static final long serialVersionUID = -96438631157065039L;

        private final Integer matchId;
        
        private final DropDownChoice<Round> round;
        private final TextField<String> date;
        private final TextField<String> name;
        private final DropDownChoice<BetType> betType;
        private final DropDownChoice<Team> teamA;
        private final DropDownChoice<Team> teamB;
        private final TextField<Integer> scoreA;
        private final TextField<Integer> scoreB;
        
        public MatchForm(String wicketId, final Integer matchId) {
            
            super(wicketId);
            
            this.matchId = matchId;
            
            this.round = new DropDownChoice<Round>("round", new Model<Round>(), Arrays.asList(Round.ALL_ROUNDS));
            this.round.setRequired(true);
            add(this.round);
            
            this.date = new TextField<String>("date", new Model<String>());
            this.date.setRequired(true);
            add(this.date);
            
            this.name = new TextField<String>("name", new Model<String>());
            this.name.setRequired(true);
            add(this.name);

            this.betType = new DropDownChoice<BetType>("betType", new Model<BetType>(), Arrays.asList(BetType.ALL_BET_TYPES));
            this.betType.setRequired(true);
            add(this.betType);

            this.teamA = new DropDownChoice<Team>("teamA", new Model<Team>(), Arrays.asList(Team.ALL_TEAMS));
            this.teamA.setNullValid(true);
            add(this.teamA);

            this.teamB = new DropDownChoice<Team>("teamB", new Model<Team>(), Arrays.asList(Team.ALL_TEAMS));
            this.teamB.setNullValid(true);
            add(this.teamB);

            this.scoreA = new TextField<Integer>("scoreA", new Model<Integer>());
            this.scoreA.add(new MinimumValidator(Integer.valueOf(0)));
            this.scoreA.setType(Integer.class);
            add(this.scoreA);

            this.scoreB = new TextField<Integer>("scoreB", new Model<Integer>());
            this.scoreB.add(new MinimumValidator(Integer.valueOf(0)));
            this.scoreB.setType(Integer.class);
            add(this.scoreB);

            
            add(new Link<Object>("clear") {

                private static final long serialVersionUID = 94123L;

                @Override
                public void onClick() {
                    setResponsePage(MatchAdminPage.class);
                }
                
            });
            
            
            if (matchId != null) {
                
                final Match match = 
                        NComploApplication.get().getMatchService().getMatch(matchId);
                
                this.name.setModelObject(match.getName());
                this.betType.setModelObject(match.getBetType());
                this.round.setModelObject(match.getRound());
                this.date.setModelObject(DateUtils.toString(match.getDate()));
                this.teamA.setModelObject(match.getTeamA());
                this.teamB.setModelObject(match.getTeamB());
                this.scoreA.setModelObject(match.getScoreA());
                this.scoreB.setModelObject(match.getScoreB());
                
            }
            
        }



        
        @Override
        protected void onSubmit() {
            
            super.onSubmit();
            
            final MatchService matchService = NComploApplication.get().getMatchService();

            try {
                
            if (this.matchId != null) {
                
                matchService.updateMatch(
                        this.matchId,
                        this.name.getModelObject(), 
                        this.betType.getModelObject(), 
                        this.round.getModelObject(), 
                        DateUtils.toCalendar(this.date.getModelObject()), 
                        this.teamA.getModelObject(), 
                        this.teamB.getModelObject(), 
                        this.scoreA.getModelObject(), 
                        this.scoreB.getModelObject());
                
            } else {
                
                matchService.addMatch(
                        this.name.getModelObject(), 
                        this.betType.getModelObject(), 
                        this.round.getModelObject(), 
                        DateUtils.toCalendar(this.date.getModelObject()), 
                        this.teamA.getModelObject(), 
                        this.teamB.getModelObject(), 
                        this.scoreA.getModelObject(), 
                        this.scoreB.getModelObject());
                
            }
            
            } catch (final ParseException e) {
                throw new InternalErrorException("Error parsing date", e);
            }
            
            setResponsePage(MatchAdminPage.class);
            
        }

        
        
    }
    
    
}
