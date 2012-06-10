package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class BetTypeBean implements Serializable {
    
    private static final long serialVersionUID = 7297004126853517111L;
    
    @NotNull
    private Integer id;
    
    @NotNull
    @Length(min=3,max=200)
    private String name;
    
    @NotNull
    private List<LangBean> namesByLang = new ArrayList<LangBean>();

    @NotNull
    private String spec;

    @NotNull
    private boolean sidesMatter = true;

    @NotNull
    private boolean scoreMatter = true;

    @NotNull
    private boolean resultMatter = true;
    
    
    public BetTypeBean() {
        super();
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(final Integer id) {
        this.id = id;
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


    public String getSpec() {
        return this.spec;
    }


    public void setSpec(final String spec) {
        this.spec = spec;
    }


    public boolean isSidesMatter() {
        return this.sidesMatter;
    }


    public void setSidesMatter(final boolean sidesMatter) {
        this.sidesMatter = sidesMatter;
    }


    public boolean isScoreMatter() {
        return this.scoreMatter;
    }


    public void setScoreMatter(final boolean scoreMatter) {
        this.scoreMatter = scoreMatter;
    }


    public boolean isResultMatter() {
        return this.resultMatter;
    }


    public void setResultMatter(final boolean resultMatter) {
        this.resultMatter = resultMatter;
    }

    
    
    
}
