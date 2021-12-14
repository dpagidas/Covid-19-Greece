package com.unipi.application.corona.models.projection;

import java.sql.Timestamp;

public interface CasesListByRegionAndByDate {

    Timestamp getDate();

    Integer getCases();

    String getAreaGr();

    Integer getAreaId();
}
