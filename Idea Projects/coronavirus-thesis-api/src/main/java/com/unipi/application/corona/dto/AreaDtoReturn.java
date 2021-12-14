package com.unipi.application.corona.dto;



public class AreaDtoReturn {

    private Long id;

    public String areaEn;

    public String areaGr;

    public Double latitude;

    public Double longtitude;

    public String regionEn;

    public String regionGr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaEn() {
        return areaEn;
    }

    public void setAreaEn(String areaEn) {
        this.areaEn = areaEn;
    }

    public String getAreaGr() {
        return areaGr;
    }

    public void setAreaGr(String areaGr) {
        this.areaGr = areaGr;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public String getRegionEn() {
        return regionEn;
    }

    public void setRegionEn(String regionEn) {
        this.regionEn = regionEn;
    }

    public String getRegionGr() {
        return regionGr;
    }

    public void setRegionGr(String regionGr) {
        this.regionGr = regionGr;
    }
}