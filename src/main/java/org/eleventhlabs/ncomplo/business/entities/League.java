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
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="LEAGUE")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ElementCollection(fetch=FetchType.EAGER,targetClass=java.lang.String.class)
    @CollectionTable(name="LEAGUE_NAME",joinColumns=@JoinColumn(name="LEAGUE_ID"))
    @MapKeyColumn(name="LANG",nullable=false,length=3)
    @Column(name="NAME", nullable=false,length=200)
    @NotNull
    @Length(min=2,max=30)
    private Map<String,String> names = new HashMap<String, String>();
    
    
    @ManyToOne
    @JoinColumn(name="COMPETITION_ID")
    @NotNull
    private Competition competition;

    
    
    public League() {
        super();
    }


    public Map<String, String> getNames() {
        return this.names;
    }


    public Competition getCompetition() {
        return this.competition;
    }


    public void setCompetition(final Competition competition) {
        this.competition = competition;
    }


    public Integer getId() {
        return this.id;
    }
    
    
    public String getName(final Locale locale) {
        return I18nUtils.getTextForLocale(this.names, locale);
    }
    
}
