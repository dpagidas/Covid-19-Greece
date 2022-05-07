package com.unipi.application.corona.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="females")
public class Females implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(mappedBy = "females" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Cases cases;

    @OneToOne(mappedBy = "females" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Critical critical;

    @OneToOne(mappedBy = "females" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Deaths deaths;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_age_distribution_id" , referencedColumnName = "id")
    private GenderAgeDistribution genderAgeDistribution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cases getCases() {
        return cases;
    }

    public void setCases(Cases cases) {
        this.cases = cases;
    }

    public Critical getCritical() {
        return critical;
    }

    public void setCritical(Critical critical) {
        this.critical = critical;
    }

    public Deaths getDeaths() {
        return deaths;
    }

    public void setDeaths(Deaths deaths) {
        this.deaths = deaths;
    }

    public GenderAgeDistribution getGenderAgeDistribution() {
        return genderAgeDistribution;
    }

    public void setGenderAgeDistribution(GenderAgeDistribution genderAgeDistribution) {
        this.genderAgeDistribution = genderAgeDistribution;
    }
}
