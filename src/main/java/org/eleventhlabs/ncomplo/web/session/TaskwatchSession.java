package org.eleventhlabs.ncomplo.web.session;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

public class TaskwatchSession extends WebSession {

    private static final long serialVersionUID = -600720020751319712L;

    
    
    
    public TaskwatchSession(Request request) {
        super(request);
    }

    
    
    public static TaskwatchSession get() {
        return ((TaskwatchSession)Session.get());
    }
    
}
