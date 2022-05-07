package com.unipi.application.corona.dto;


import java.sql.Timestamp;

public class CasesListByDateDtoReturn {

    private Integer confirmedCases;

    public Integer confirmedDeaths;

    public Integer intensiveCare;

    public Double positivityIndex;

    public Timestamp date;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(Integer confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public Integer getConfirmedDeaths() {
        return confirmedDeaths;
    }

    public void setConfirmedDeaths(Integer confirmedDeaths) {
        this.confirmedDeaths = confirmedDeaths;
    }

    public Integer getIntensiveCare() {
        return intensiveCare;
    }

    public void setIntensiveCare(Integer intensiveCare) {
        this.intensiveCare = intensiveCare;
    }

    public Double getPositivityIndex() {
        return positivityIndex;
    }

    public void setPositivityIndex(Double positivityIndex) {
        this.positivityIndex = positivityIndex;
    }
}