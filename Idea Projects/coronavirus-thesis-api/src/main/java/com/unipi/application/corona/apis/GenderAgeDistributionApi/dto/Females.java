package com.unipi.application.corona.apis.GenderAgeDistributionApi.dto;

public class Females {

    public Cases cases;

    public Critical critical;

    public Deaths deaths;

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
}
