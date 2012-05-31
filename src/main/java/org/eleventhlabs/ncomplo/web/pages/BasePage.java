package org.eleventhlabs.ncomplo.web.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;

public abstract class BasePage extends WebPage {

    private static final String CSS = "css/styles.css";
    
    public BasePage() {
        this(null);
    }

    public BasePage(PageParameters parameters) {
        super(parameters);
        add(CSSPackageResource.getHeaderContribution(CSS));
    }
    
    
}
