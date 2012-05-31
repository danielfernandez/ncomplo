package org.eleventhlabs.ncomplo.web.pages;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.eleventhlabs.ncomplo.web.application.NComploApplication;

public class BetAdminPage extends BaseAdminPage {

    private static final long serialVersionUID = 512309121L;

    
    public BetAdminPage() {
        super();
        add(new BookmarkablePageLink<Object>("linkMatchAdmin", MatchAdminPage.class));
        add(new FeedbackPanel("feedbackPanel"));
        add(new ExistingOwnerForm("existingOwnerForm"));
        add(new NewOwnerForm("newOwnerForm"));
    }
    
    
    

    
    
    
    private static class ExistingOwnerForm extends Form<Object> {

        private static final long serialVersionUID = -96438631157065030L;
        
        private final DropDownChoice<String> ownerName;
        
        
        public ExistingOwnerForm(String id) {
            
             super(id);
             
             this.ownerName = 
                 new DropDownChoice<String>("ownerName", new Model<String>(), new ExistingOwnersModel());
             this.ownerName.setRequired(true);
             add(this.ownerName);
             
        }


        @Override
        protected void onSubmit() {
            super.onSubmit();
            setResponsePage(new BetsForOwnerAdminPage(this.ownerName.getModelObject()));
        }
        
    }
    
    
    
    private static class ExistingOwnersModel extends LoadableDetachableModel<List<String>> {

        private static final long serialVersionUID = 76824L;

        public ExistingOwnersModel() {
            super();
        }
        
        @Override
        protected List<String> load() {
            return NComploApplication.get().getBetService().findAllBetOwnerNames();
        }
        
    }
    
    
    
    private static class NewOwnerForm extends Form<Object> {

        private static final long serialVersionUID = -26438131157065030L;
        
        private final TextField<String> ownerName;
        
        
        public NewOwnerForm(String id) {
            
             super(id);
             
             this.ownerName = new TextField<String>("ownerName", new Model<String>());
             this.ownerName.setRequired(true);
             add(this.ownerName);
             
        }


        @Override
        protected void onSubmit() {
            super.onSubmit();
            setResponsePage(new BetsForOwnerAdminPage(this.ownerName.getModelObject()));
        }
        
    }
    
    
    
}
