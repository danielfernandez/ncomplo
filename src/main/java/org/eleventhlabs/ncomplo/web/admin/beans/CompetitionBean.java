package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;


public class CompetitionBean implements Serializable {
    
    private static final long serialVersionUID = 7297004126853517111L;
    
    @NotNull
    private Integer id;
    
    @NotNull
    
    private Map<String,String> names = new HashMap<String, String>();

    @NotNull
    private boolean active = true;

    
    public CompetitionBean() {
        super();
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public Map<String, String> getNames() {
        return this.names;
    }


    public void setNames(final Map<String, String> names) {
        this.names = names;
    }


    public boolean isActive() {
        return this.active;
    }


    public void setActive(final boolean active) {
        this.active = active;
    }

    
    
    
}
