package org.eleventhlabs.ncomplo.business.entities;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eleventhlabs.ncomplo.business.util.I18nUtils;


@Entity
@Table(name="COMPETITION")
public class Competition implements I18nNamedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    @Column(name="NAME",nullable=false,length=200)
    private String name;
    
    
    @ElementCollection(fetch=FetchType.EAGER,targetClass=java.lang.String.class)
    @CollectionTable(name="COMPETITION_NAME_I18N",joinColumns=@JoinColumn(name="COMPETITION_ID"))
    @MapKeyColumn(name="LANG",nullable=false,length=20)
    @Column(name="NAME", nullable=false,length=200)
    private Map<String,String> namesByLang = new LinkedHashMap<String, String>();

    
    @Column(name="ACTIVE",nullable=false)
    private boolean active = true;

    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="competition")
    private Set<BetType> betTypes = new LinkedHashSet<BetType>();

    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="competition")
    private Set<Round> rounds = new LinkedHashSet<Round>();

    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="competition")
    private Set<GameSide> gameSides = new LinkedHashSet<GameSide>();

    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="competition")
    private Set<Game> games = new LinkedHashSet<Game>();
    
    
    
    
    public Competition() {
        super();
    }


    @Override
    public Map<String, String> getNamesByLang() {
        return this.namesByLang;
    }


    public Integer getId() {
        return this.id;
    }


    @Override
    public String getName(final Locale locale) {
        return I18nUtils.getTextForLocale(locale, this.namesByLang, this.name);
    }


    public boolean isActive() {
        return this.active;
    }


    public void setActive(final boolean active) {
        this.active = active;
    }
    

    public Set<BetType> getBetTypes() {
        return this.betTypes;
    }
    

    public Set<Round> getRounds() {
        return this.rounds;
    }
    

    public Set<GameSide> getGameSides() {
        return this.gameSides;
    }
    

    public Set<Game> getGames() {
        return this.games;
    }


    @Override
    public String getName() {
        return this.name;
    }


    public void setName(final String name) {
        this.name = name;
    }
    
    
    
}
