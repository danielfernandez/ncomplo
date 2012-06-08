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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eleventhlabs.ncomplo.business.util.I18nUtils;


@Entity
@Table(name="LEAGUE")
public class League implements I18nNamedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    @Column(name="NAME",nullable=false,length=200)
    private String name;
    
    
    @ElementCollection(fetch=FetchType.EAGER,targetClass=java.lang.String.class)
    @CollectionTable(name="LEAGUE_NAME_I18N",joinColumns=@JoinColumn(name="LEAGUE_ID"))
    @MapKeyColumn(name="LANG",nullable=false,length=20)
    @Column(name="NAME", nullable=false,length=200)
    private Map<String,String> namesByLang = new LinkedHashMap<String, String>();

    
    @Column(name="ACTIVE",nullable=false)
    private boolean active = true;
    
    
    @ManyToOne
    @JoinColumn(name="COMPETITION_ID",nullable=false)
    private Competition competition; 

    
    @Column(name="ADMIN_EMAIL",nullable=false)
    private String adminEmail;

    
    @ManyToMany
    private Set<User> participants = new LinkedHashSet<User>();

    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="league")
    @MapKey(name="game")
    private Map<Game,LeagueGame> leagueGames = new LinkedHashMap<Game,LeagueGame>();

    
    
    
    public League() {
        super();
    }


    

    @Override
    public String getName() {
        return this.name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public boolean isActive() {
        return this.active;
    }


    public void setActive(final boolean active) {
        this.active = active;
    }


    public Competition getCompetition() {
        return this.competition;
    }


    public void setCompetition(final Competition competition) {
        this.competition = competition;
    }


    public String getAdminEmail() {
        return this.adminEmail;
    }


    public void setAdminEmail(final String adminEmail) {
        this.adminEmail = adminEmail;
    }


    public Integer getId() {
        return this.id;
    }


    @Override
    public Map<String, String> getNamesByLang() {
        return this.namesByLang;
    }


    public Set<User> getParticipants() {
        return this.participants;
    }


    @Override
    public String getName(final Locale locale) {
        return I18nUtils.getTextForLocale(locale, this.namesByLang, this.name);
    }

    
    public Map<Game,LeagueGame> getLeagueGames() {
        return this.leagueGames;
    }
    
    
    
}
