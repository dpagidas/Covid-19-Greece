package com.unipi.application.corona.dto;

import java.sql.Timestamp;

public class TotalTestByDateDtoReturn {

    private Long id;

    public Integer rapidTests;

    public Integer tests;

    public Integer allTests;

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
        this.rapidTests = rapidTests;
    }

    public Integer getTests() {
        return tests;
    }

    public void setTests(Integer tests) {
        this.tests = tests;
    }

    public Integer getAllTests() {
        return allTests;
    }

    public void setAllTests(Integer allTests) {
        this.allTests = allTests;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}