package com.unipi.application.corona.models;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Areas")
public class Areas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="area_en")
    public String areaEn;

    @Column(name="area_gr")
    public String areaGr;

    @Column(name="latitude")
    public Double latitude;

    @Column(name="cases_per_100000_people")
    public Double cases_per_100000_people;

    @Column(name="geo_department_en")
    public String geo_department_en;

    @Column(name="geo_department_gr")
    public String geo_department_gr;

    @Column(name="population")
    public Integer population;

    @Column(name="longtitude")
    public Double longtitude;

    @Column(name="region_en")
    public String regionEn;

    @Column(name="last_updated_at")
    public String last_updated_at;

    @Column(name="region_gr")
    public String regionGr;

    @Column(name="total_cases")
    public Double total_cases;

    @OneToMany(mappedBy = "areas" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public List<Region> regions;

    @OneToMany(mappedBy = "areas" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public List<VaccinationInfo> vaccinationInfos;

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

    public String getRegionEn() {
        return regionEn;
    }

    public void setRegionEn(String regionEn) {
        this.regionEn = regionEn;
    }

    public String getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(String last_updated_at) {
        this.last_updated_at = last_updated_at;
    }

    public String getRegionGr() {
        return regionGr;
    }

    public void setRegionGr(String regionGr) {
        this.regionGr = regionGr;
    }

    public Double getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(Double total_cases) {
        this.total_cases = total_cases;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<VaccinationInfo> getVaccinationInfos() {
        return vaccinationInfos;
    }

    public void setVaccinationInfos(List<VaccinationInfo> vaccinationInfos) {
        this.vaccinationInfos = vaccinationInfos;
    }
}
