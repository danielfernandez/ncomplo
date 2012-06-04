package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;


public class BetTypeBean implements Serializable {
    
    private static final long serialVersionUID = 7297004126853517111L;
    
    @NotNull
    private Integer id;

    @NotNull
    private Integer competitionId;
    
    @NotNull
    private Map<String,String> names = new HashMap<String, String>();

    
    public BetTypeBean() {
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


    public Integer getCompetitionId() {
        return this.competitionId;
    }


    public void setCompetitionId(final Integer competitionId) {
        this.competitionId = competitionId;
    }

    
    
    
}
