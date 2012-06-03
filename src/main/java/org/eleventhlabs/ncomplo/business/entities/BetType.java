package org.eleventhlabs.ncomplo.business.entities;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eleventhlabs.ncomplo.business.util.I18nUtils;


@Entity
@Table(name="BET_TYPE")
public class BetType {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    @ElementCollection(fetch=FetchType.EAGER,targetClass=java.lang.String.class)
    @CollectionTable(name="BET_TYPE_NAME",joinColumns=@JoinColumn(name="BET_TYPE_ID"))
    @MapKeyColumn(name="LANG",nullable=false,length=3)
    @Column(name="NAME", nullable=false,length=200)
    private Map<String,String> names = new HashMap<String, String>();

    
    @ManyToOne
    @JoinColumn(name="COMPETITION_ID")
    @NotNull
    private Competition competition;



    public BetType() {
        super();
    }



    public Map<String, String> getNames() {
        return this.names;
    }



    public void setNames(final Map<String, String> names) {
        this.names = names;
    }



    public Integer getId() {
        return this.id;
    }



    public Competition getCompetition() {
        return this.competition;
    }



    public void setCompetition(final Competition competition) {
        this.competition = competition;
    }

    
    
    public String getName(final Locale locale) {
        return I18nUtils.getTextForLocale(this.names, locale);
    }


    
    
    
}
