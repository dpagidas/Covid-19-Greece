package com.unipi.application.corona.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the VACCINATION_INFO database table.
 *
 */
@Entity
@Table(name="VACCINATION_INFO")
public class VaccinationInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="dailydose1", nullable=false)
    private Integer dailydose1;

    @Column(name="dailydose2", nullable=false)
    private Integer dailydose2;

    @Column(name="dailydose3", nullable=false)
    private Integer dailydose3;

    @Column(name="daydiff", nullable=false)
    private Integer daydiff;

    @Column(name="daytotal", nullable=false)
    private Integer daytotal;

    @Column(name="referencedate", nullable=false)
    private Timestamp referencedate;

    @Column(name="totaldistinctpersons", nullable=false)
    private Integer totaldistinctpersons;

    @Column(name="totaldose1", nullable=false)
    private Integer totaldose1;

    @Column(name="totaldose2", nullable=false)
    private Integer totaldose2;

    @Column(name="totaldose3", nullable=false)
    private Integer totaldose3;

    @Column(name="total_vaccinations", nullable=false)
    private Integer totalvaccinations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id" , referencedColumnName = "id")
    private Areas areas;

    public Integer getDailydose3() {
        return dailydose3;
    }

    public void setDailydose3(Integer dailydose3) {
        this.dailydose3 = dailydose3;
    }

    public Integer getTotaldose3() {
        return totaldose3;
    }

    public void setTotaldose3(Integer totaldose3) {
        this.totaldose3 = totaldose3;
    }

    public VaccinationInfo() {
    }

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

    public Integer getTotalvaccinations() {
        return totalvaccinations;
    }

    public void setTotalvaccinations(Integer totalvaccinations) {
        this.totalvaccinations = totalvaccinations;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }
}
