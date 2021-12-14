package com.unipi.application.corona.apis.RegionCasesHistory.dto;


public class RegionDto {

    public String area_en;

    public String area_gr;

    public Integer cases;

    public String geo_department_en;

    public String geo_department_gr;

    public String last_updated_at;

    public Double latitude;

    public Double longtitude;

    public String region_en;

    public String region_gr;

    public String getArea_en() {
        return area_en;
    }

    public void setArea_en(String area_en) {
        this.area_en = area_en;
    }

    public String getArea_gr() {
        return area_gr;
    }

    public void setArea_gr(String area_gr) {
        this.area_gr = area_gr;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public String getGeo_department_en() {
        return geo_department_en;
    }

    public void setGeo_department_en(String geo_department_en) {
        this.geo_department_en = geo_department_en;
    }

    public String getGeo_department_gr() {
        return geo_department_gr;
    }

    public void setGeo_department_gr(String geo_department_gr) {
        this.geo_department_gr = geo_department_gr;
    }

    public String getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(String last_updated_at) {
        this.last_updated_at = last_updated_at;
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

    public String getRegion_en() {
        return region_en;
    }

    public void setRegion_en(String region_en) {
        this.region_en = region_en;
    }

    public String getRegion_gr() {
        return region_gr;
    }

    public void setRegion_gr(String region_gr) {
        this.region_gr = region_gr;
    }
}