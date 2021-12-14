package com.unipi.application.corona.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="regions")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

//    @Column(name="area_en")
//    public String areaEn;
//
//    @Column(name="area_gr")
//    public String areaGr;

    @Column(name="cases")
    public Integer cases;

    @Column(name="geo_department_en")
    public String geoDepartmentEn;

    @Column(name="geo_department_gr")
    public String geoDepartmentGr;

    @Column(name="last_updated_at")
    public String lastUpdatedAt;

//    @Column(name="latitude")
//    public Double latitude;
//
//    @Column(name="longtitude")
//    public Double longtitude;

//    @Column(name="region_en")
//    public String regionEn;
//
//    @Column(name="region_gr")
//    public String regionGr;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id" , referencedColumnName = "id")
    private Areas areas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_case_history_id" , referencedColumnName = "id")
    private RegionCasesHistory regionCasesHistory;



    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegionCasesHistory getRegionCasesHistory() {
        return regionCasesHistory;
    }

    public void setRegionCasesHistory(RegionCasesHistory regionCasesHistory) {
        this.regionCasesHistory = regionCasesHistory;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
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


}
