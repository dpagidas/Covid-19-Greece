package com.unipi.application.corona.models.projection;

import java.sql.Timestamp;

public interface CasesListByDate {

    Timestamp getDate();

    Integer getConfirmedCases();

    Integer getConfirmedDeaths();

    Integer getIntensiveCare();

    Integer getTests();

    Integer getRapidTests();
}
