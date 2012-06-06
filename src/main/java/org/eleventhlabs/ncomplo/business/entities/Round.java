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
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.eleventhlabs.ncomplo.business.util.I18nUtils;



@Entity
@Table(name="ROUND")
public class Round implements Comparable<Round> {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    
    @Column(name="NAME",nullable=false,length=200)
    private String name;
    
    
    @ElementCollection(fetch=FetchType.EAGER,targetClass=java.lang.String.class)
    @CollectionTable(name="ROUND_NAME_I18N",joinColumns=@JoinColumn(name="ROUND_ID"))
    @MapKeyColumn(name="LANG",nullable=false,length=20)
    @Column(name="NAME", nullable=false,length=200)
    private Map<String,String> namesByLang = new LinkedHashMap<String, String>();
    
    
    @ManyToOne
    @JoinColumn(name="COMPETITION_ID",nullable=false)
    private Competition competition;

    
    @Column(name="ROUND_ORDER",nullable=false)
    private Integer order;

    
    
    public Round() {
        super();
    }



    public String getName() {
        return this.name;
    }



    public void setName(final String name) {
        this.name = name;
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



    public Map<String, String> getNamesByLang() {
        return this.namesByLang;
    }


    public String getName(final Locale locale) {
        return I18nUtils.getTextForLocale(locale, this.namesByLang, this.name);
    }


    public Integer getOrder() {
        return this.order;
    }


    public void setOrder(final Integer order) {
        this.order = order;
    }



    
    @Override
    public int compareTo(final Round o) {
        return this.order.compareTo(o.order);
    }

    
    
}
