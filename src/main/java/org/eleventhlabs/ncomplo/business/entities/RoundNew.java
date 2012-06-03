package org.eleventhlabs.ncomplo.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="ROUND")
public class RoundNew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="NAME")
    @NotNull
    @Length(min=1,max=30)
    private String name;

    
    private final String localizedName;
    
    private RoundNew(final String localizedName) {
        this.localizedName = localizedName;
    }

    @Override
    public String toString() {
        return this.localizedName;
    }
    
}
