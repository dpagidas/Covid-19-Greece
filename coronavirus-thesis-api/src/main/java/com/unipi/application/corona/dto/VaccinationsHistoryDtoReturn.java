package com.unipi.application.corona.dto;

import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.VaccinationInfo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

public class VaccinationsHistoryDtoReturn {

    private Long id;

    private Integer dailydose1;

    private Integer dailydose2;

    private Integer dailydose3;

    private Integer daydiff;

    private Integer daytotal;

    private Timestamp referencedate;

    private Integer totaldistinctpersons;

    private Integer totaldose1;

    private Integer totaldose2;

    private Integer totaldose3;

    private Integer totalvaccinations;

    private AreaDtoReturn areas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AreaDtoReturn getAreas() {
        return areas;
    }

    public void setAreas(AreaDtoReturn areas) {
        this.areas = areas;
    }
}
