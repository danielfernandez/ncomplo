package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.eleventhlabs.ncomplo.business.entities.BetType;
import org.eleventhlabs.ncomplo.business.entities.Competition;
import org.eleventhlabs.ncomplo.business.entities.Round;
import org.eleventhlabs.ncomplo.business.entities.Team;
import org.hibernate.validator.constraints.Length;

public class MatchBean implements Serializable {
    
    private static final long serialVersionUID = 3385419487307786824L;
    
    
    private Integer id;
    private Integer competitionId;
    private String name;
    private BetType betType;

    @Column(name="ROUND")
    @NotNull
    private Round round;
    
    @Column(name="DATE")
    @NotNull
    private Calendar date;
    
    @Column(name="TEAM_A")
    private Team teamA;
    
    @Column(name="TEAM_B")
    private Team teamB;
    
    @Column(name="SCORE_A")
    private Integer scoreA;
    
    @Column(name="SCORE_B")
    private Integer scoreB;
    
    
    
}
