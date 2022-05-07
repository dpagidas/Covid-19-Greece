package com.unipi.application.corona.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="deaths")
public class Deaths implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="age_0_17")
    public Integer age_0_17;

    @Column(name="age_18_39")
    public Integer age_18_39;

    @Column(name="age_40_64")
    public Integer age_40_64;

    @Column(name="age_65")
    public Integer age_65;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "males_id" , referencedColumnName = "id")
    private Males males;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "females_id" , referencedColumnName = "id")
    private Females females;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge_0_17() {
        return age_0_17;
    }

    public void setAge_0_17(Integer age_0_17) {
        this.age_0_17 = age_0_17;
    }

    public Integer getAge_18_39() {
        return age_18_39;
    }

    public void setAge_18_39(Integer age_18_39) {
        this.age_18_39 = age_18_39;
    }

    public Integer getAge_40_64() {
        return age_40_64;
    }

    public void setAge_40_64(Integer age_40_64) {
        this.age_40_64 = age_40_64;
    }

    public Integer getAge_65() {
        return age_65;
    }

    public void setAge_65(Integer age_65) {
        this.age_65 = age_65;
    }

    public Males getMales() {
        return males;
    }

    public void setMales(Males males) {
        this.males = males;
    }

    public Females getFemales() {
        return females;
    }

    public void setFemales(Females females) {
        this.females = females;
    }
}
