package org.eleventhlabs.ncomplo.web.application;

import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.ISessionStore;
import org.eleventhlabs.ncomplo.business.services.BetService;
import org.eleventhlabs.ncomplo.business.services.MatchNewService;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.eleventhlabs.ncomplo.web.pages.BetAdminPage;
import org.eleventhlabs.ncomplo.web.pages.BetsDetailPage;
import org.eleventhlabs.ncomplo.web.pages.Forbidden;
import org.eleventhlabs.ncomplo.web.pages.MatchAdminPage;
import org.eleventhlabs.ncomplo.web.pages.ScoreboardPage;
import org.eleventhlabs.ncomplo.web.session.TaskwatchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NComploApplication extends WebApplication {

    @Autowired
    private UserService aaaService;
    
    @Autowired
    private BetService betService;
    
    @Autowired
    private MatchNewService matchService;
    
    
    
    @Override
    public Class<? extends Page> getHomePage() {
        return ScoreboardPage.class;
    }

    
    @Override
    protected void init() {
        super.init();
        getResourceSettings().setResourcePollFrequency(null);
        mountBookmarkablePage("/scoreboard", ScoreboardPage.class);
        mountBookmarkablePage("/betsDetail", BetsDetailPage.class);
        mountBookmarkablePage("/matchAdmin", MatchAdminPage.class);
        mountBookmarkablePage("/betAdmin", BetAdminPage.class);
        mountBookmarkablePage("/forbidden", Forbidden.class);
    }

    
    @Override
    protected ISessionStore newSessionStore() {
        return new HttpSessionStore(this);
    }


    @Override
    public Session newSession(Request request, Response response) {
        final Session session = new TaskwatchSession(request);
        session.setLocale(new Locale("es", "ES"));
        return session;
    }

    
    
    public static final NComploApplication get() {
        return (NComploApplication) Application.get();
    }

    

    public final UserService getAaaService() {
        return this.aaaService;
    }


    public final BetService getBetService() {
        return this.betService;
    }

    public final MatchNewService getMatchService() {
        return this.matchService;
    }
    
    
}
