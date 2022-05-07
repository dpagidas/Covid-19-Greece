package com.unipi.application.corona.dto;


public class RegionDtoReturn {

    private Long id;

    public Integer cases;

    public String geoDepartmentEn;

    public String geoDepartmentGr;

    public String lastUpdatedAt;

    private AreaDtoReturn areas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public String getGeoDepartmentEn() {
        return geoDepartmentEn;
    }

    public void setGeoDepartmentEn(String geoDepartmentEn) {
        this.geoDepartmentEn = geoDepartmentEn;
    }

    public String getGeoDepartmentGr() {
        return geoDepartmentGr;
    }

    public void setGeoDepartmentGr(String geoDepartmentGr) {
        this.geoDepartmentGr = geoDepartmentGr;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public AreaDtoReturn getAreas() {
        return areas;
    }

    public void setAreas(AreaDtoReturn areas) {
        this.areas = areas;
    }
}