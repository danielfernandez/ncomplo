package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class MatchBean implements Serializable {

    private static final long serialVersionUID = 9180456877660425744L;

    
    @NotNull
    private Integer id;
    
    @NotNull
    @Length(min=3,max=200)
    private String name;
    
    @NotNull
    private List<LangBean> namesByLang = new ArrayList<LangBean>();

    
    @NotNull
    private Integer roundId;
    
    
    @NotNull
    private Integer defaultBetTypeId;
    
    
    private Date date;
    
    
    private Integer teamAId;
    
    
    private Integer teamBId;
    
    
    private Integer scoreA;
    
    
    private Integer scoreB;
    
    
    
    
    public MatchBean() {
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


    public Integer getRoundId() {
        return this.roundId;
    }


    public void setRoundId(final Integer roundId) {
        this.roundId = roundId;
    }


    public Integer getDefaultBetTypeId() {
        return this.defaultBetTypeId;
    }


    public void setDefaultBetTypeId(final Integer defaultBetTypeId) {
        this.defaultBetTypeId = defaultBetTypeId;
    }


    public Date getDate() {
        return this.date;
    }


    public void setDate(final Date date) {
        this.date = date;
    }


    public Integer getTeamAId() {
        return this.teamAId;
    }


    public void setTeamAId(final Integer teamAId) {
        this.teamAId = teamAId;
    }


    public Integer getTeamBId() {
        return this.teamBId;
    }


    public void setTeamBId(final Integer teamBId) {
        this.teamBId = teamBId;
    }


    public Integer getScoreA() {
        return this.scoreA;
    }


    public void setScoreA(final Integer scoreA) {
        this.scoreA = scoreA;
    }


    public Integer getScoreB() {
        return this.scoreB;
    }


    public void setScoreB(final Integer scoreB) {
        this.scoreB = scoreB;
    }


    public List<LangBean> getNamesByLang() {
        return this.namesByLang;
    }
    
    
    
}
