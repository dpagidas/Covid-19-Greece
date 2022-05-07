package com.unipi.application.corona.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="gender_age_distribution")
public class GenderAgeDistribution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(mappedBy = "genderAgeDistribution" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Females females;

    @OneToOne(mappedBy = "genderAgeDistribution" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Males males;

    @Column(name="updated_at")
    private Timestamp updateAt;

    @Column(name="male_percentage_age_distribution")
    private Integer malePercentageAgeDistribution;

    @Column(name="female_percentage_age_distribution")
    private Integer femalePercentageAgeDistribution;

    public Integer getMalePercentageAgeDistribution() {
        return malePercentageAgeDistribution;
    }

    public void setMalePercentageAgeDistribution(Integer malePercentageAgeDistribution) {
        this.malePercentageAgeDistribution = malePercentageAgeDistribution;
    }

    public Integer getFemalePercentageAgeDistribution() {
        return femalePercentageAgeDistribution;
    }

    public void setFemalePercentageAgeDistribution(Integer femalePercentageAgeDistribution) {
        this.femalePercentageAgeDistribution = femalePercentageAgeDistribution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Females getFemales() {
        return females;
    }

    public void setFemales(Females females) {
        this.females = females;
    }

    public Males getMales() {
        return males;
    }

    public void setMales(Males males) {
        this.males = males;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
