package org.eleventhlabs.ncomplo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="LEAGUE")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="NAME")
    @NotNull
    @Length(min=1,max=30)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="COMPETITION_ID")
    @NotNull
    private Competition competition;

    
    public League() {
        super();
    }
    
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Competition getCompetition() {
        return this.competition;
    }


    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
    
    
}
