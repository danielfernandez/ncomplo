package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class CompetitionBean implements Serializable {
    
    private static final long serialVersionUID = 7297004126853517111L;
    
    @NotNull
    private Integer id;
    
    @NotNull
    @Length(min=3,max=200)
    private String name;
    
    @NotNull
    private List<LangBean> namesByLang = new ArrayList<LangBean>();

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


    public boolean isActive() {
        return this.active;
    }


    public void setActive(final boolean active) {
        this.active = active;
    }



    public String getName() {
        return this.name;
    }



    public void setName(final String name) {
        this.name = name;
    }



    public List<LangBean> getNamesByLang() {
        return this.namesByLang;
    }

    
    
    
}
