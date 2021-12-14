package com.unipi.application.corona.dto;

import java.sql.Timestamp;

public class ConfirmedDeathsDtoReturn {

    private Long id;

    public Integer deathsPerDay;

    public Integer allDeaths;

    public Timestamp date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeathsPerDay() {
        return deathsPerDay;
    }

    public void setDeathsPerDay(Integer deathsPerDay) {
        this.deathsPerDay = deathsPerDay;
    }

    public Integer getAllDeaths() {
        return allDeaths;
    }

    public void setAllDeaths(Integer allDeaths) {
        this.allDeaths = allDeaths;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}