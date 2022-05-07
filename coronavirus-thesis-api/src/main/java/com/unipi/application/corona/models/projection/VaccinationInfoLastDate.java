package com.unipi.application.corona.models.projection;

import java.sql.Timestamp;

public interface VaccinationInfoLastDate {

    Timestamp getReferenceDate();

    Integer getSumTotalDose2();

    Integer getSumTotalDose3();

    Integer getSumTotalDistinctPersons();

    Integer getSumTotalVaccinations();

    String getAreaGr();

    Integer getAreaId();
}
