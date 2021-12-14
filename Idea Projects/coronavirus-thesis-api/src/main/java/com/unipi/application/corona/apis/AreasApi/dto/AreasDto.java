package com.unipi.application.corona.apis.AreasApi.dto;


import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AreasDto {

    public String area_en;

    public String area_gr;

    public Double latitude;

    public Double cases_per_100000_people;

    public String geo_department_en;

    public String geo_department_gr;

    public Integer population;

    public Double longtitude;

    public String region_en;

    public String last_updated_at;

    public String region_gr;

    public Double total_cases;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getCases_per_100000_people() {
        return cases_per_100000_people;
    }

    public void setCases_per_100000_people(Double cases_per_100000_people) {
        this.cases_per_100000_people = cases_per_100000_people;
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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
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

    public String getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(String last_updated_at) {
        this.last_updated_at = last_updated_at;
    }

    public String getRegion_gr() {
        return region_gr;
    }

    public void setRegion_gr(String region_gr) {
        this.region_gr = region_gr;
    }

    public Double getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(Double total_cases) {
        this.total_cases = total_cases;
    }
}