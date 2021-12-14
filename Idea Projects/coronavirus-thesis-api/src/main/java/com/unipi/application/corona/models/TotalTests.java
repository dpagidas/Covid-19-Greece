package com.unipi.application.corona.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="total_tests")
public class TotalTests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @JsonProperty("rapid-tests")
    @Column(name="rapid_tests" , columnDefinition = "integer default 0")
    public Integer rapidTests;

    @Column(name="tests" , columnDefinition = "integer default 0")
    public Integer tests;

    @Column(name="date")
    public Timestamp date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRapidTests() {
        return rapidTests;
    }

    public void setRapidTests(Integer rapidTests) {
        if(rapidTests == null){
            this.rapidTests = 0;
        }else{
            this.rapidTests = rapidTests;

        }
    }

    public Integer getTests() {
        return tests;
    }

    public void setTests(Integer tests) {

        if(tests == null){
            this.tests = 0;
        }else{
            this.tests = tests;

        }    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
