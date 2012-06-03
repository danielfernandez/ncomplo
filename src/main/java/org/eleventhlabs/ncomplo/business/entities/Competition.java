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
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eleventhlabs.ncomplo.business.util.I18nUtils;


@Entity
@Table(name="COMPETITION")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    @ElementCollection(fetch=FetchType.EAGER,targetClass=java.lang.String.class)
    @CollectionTable(name="COMPETITION_NAME",joinColumns=@JoinColumn(name="COMPETITION_ID"))
    @MapKeyColumn(name="LANG",nullable=false,length=3)
    @Column(name="NAME", nullable=false,length=200)
    private Map<String,String> names = new HashMap<String, String>();

    @Column(name="ACTIVE")
    @NotNull
    private boolean active = true;
    
    
    public Competition() {
        super();
    }


    public Map<String, String> getNames() {
        return this.names;
    }


    public Integer getId() {
        return this.id;
    }
    
    
    public String getName(final Locale locale) {
        return I18nUtils.getTextForLocale(this.names, locale);
    }


    public boolean isActive() {
        return this.active;
    }


    public void setActive(final boolean active) {
        this.active = active;
    }
    
    
    
}
