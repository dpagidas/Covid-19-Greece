package com.unipi.application.corona.dto;


import java.sql.Timestamp;

public class CasesByRegionDtoReturn {

  private Integer cases;

  private Timestamp date;

  private String areaGr;

  private Integer diffCases;

    public Integer getDiffCases() {
        return diffCases;
    }

    public void setDiffCases(Integer diffCases) {
        this.diffCases = diffCases;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAreaGr() {
        return areaGr;
    }

    public void setAreaGr(String areaGr) {
        this.areaGr = areaGr;
    }
}