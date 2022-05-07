package com.unipi.application.corona.apis.VaccinationStatisticsApi.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class VaccinationInfoDTO {

    public String area_en;
    public String area_gr;
    public Integer areaid;
    public Integer dailydose1;
    public Integer dailydose2;
    public Integer dailydose3;
    public Integer daydiff;
    public Integer daytotal;
    public Timestamp referencedate;
    public Integer totaldistinctpersons;
    public Integer totaldose1;
    public Integer totaldose2;
    public Integer totaldose3;
    public Integer totalvaccinations;

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

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Integer getDailydose1() {
        return dailydose1;
    }

    public void setDailydose1(Integer dailydose1) {
        this.dailydose1 = dailydose1;
    }

    public Integer getDailydose2() {
        return dailydose2;
    }

    public void setDailydose2(Integer dailydose2) {
        this.dailydose2 = dailydose2;
    }

    public Integer getDailydose3() {
        return dailydose3;
    }

    public void setDailydose3(Integer dailydose3) {
        this.dailydose3 = dailydose3;
    }

    public Integer getDaydiff() {
        return daydiff;
    }

    public void setDaydiff(Integer daydiff) {
        this.daydiff = daydiff;
    }

    public Integer getDaytotal() {
        return daytotal;
    }

    public void setDaytotal(Integer daytotal) {
        this.daytotal = daytotal;
    }

    public Timestamp getReferencedate() {
        return referencedate;
    }

    public void setReferencedate(Timestamp referencedate) {
        this.referencedate = referencedate;
    }

    public Integer getTotaldistinctpersons() {
        return totaldistinctpersons;
    }

    public void setTotaldistinctpersons(Integer totaldistinctpersons) {
        this.totaldistinctpersons = totaldistinctpersons;
    }

    public Integer getTotaldose1() {
        return totaldose1;
    }

    public void setTotaldose1(Integer totaldose1) {
        this.totaldose1 = totaldose1;
    }

    public Integer getTotaldose2() {
        return totaldose2;
    }

    public void setTotaldose2(Integer totaldose2) {
        this.totaldose2 = totaldose2;
    }

    public Integer getTotaldose3() {
        return totaldose3;
    }

    public void setTotaldose3(Integer totaldose3) {
        this.totaldose3 = totaldose3;
    }

    public Integer getTotalvaccinations() {
        return totalvaccinations;
    }

    public void setTotalvaccinations(Integer totalvaccinations) {
        this.totalvaccinations = totalvaccinations;
    }
}
