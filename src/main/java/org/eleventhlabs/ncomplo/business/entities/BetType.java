package org.eleventhlabs.ncomplo.business.entities;

import java.util.LinkedHashMap;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.eleventhlabs.ncomplo.business.util.I18nUtils;


@Entity
@Table(name="BET_TYPE")
public class BetType implements I18nNamedEntity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    
    @Column(name="NAME",nullable=false,length=1000)
    private String name;
    
    
    @ElementCollection(fetch=FetchType.EAGER,targetClass=java.lang.String.class)
    @CollectionTable(name="BET_TYPE_NAME_I18N",joinColumns=@JoinColumn(name="BET_TYPE_ID"))
    @MapKeyColumn(name="LANG",nullable=false,length=20)
    @Column(name="NAME", nullable=false,length=1000)
    private Map<String,String> namesByLang = new LinkedHashMap<String, String>();

    
    @Column(name="SPEC",nullable=false)
    @Lob
    private String spec;
    
    
    @ManyToOne
    @JoinColumn(name="COMPETITION_ID",nullable=false)
    private Competition competition;

    
    @Column(name="GAME_SIDES_MATTER")
    private boolean gameSidesMatter;
    
    
    @Column(name="SCORE_MATTER")
    private boolean scoreMatter;
    


    public BetType() {
        super();
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

    
    
    @Override
    public String getName(final Locale locale) {
        return I18nUtils.getTextForLocale(locale, this.namesByLang, this.name);
    }



    @Override
    public String getName() {
        return this.name;
    }



    public void setName(final String name) {
        this.name = name;
    }



    @Override
    public Map<String, String> getNamesByLang() {
        return this.namesByLang;
    }



    public String getSpec() {
        return this.spec;
    }



    public void setSpec(final String spec) {
        this.spec = spec;
    }



    public boolean isGameSidesMatter() {
        return this.gameSidesMatter;
    }



    public void setGameSidesMatter(final boolean gameSidesMatter) {
        this.gameSidesMatter = gameSidesMatter;
    }



    public boolean isScoreMatter() {
        return this.scoreMatter;
    }



    public void setScoreMatter(final boolean scoreMatter) {
        this.scoreMatter = scoreMatter;
    }


    
    
    
}
