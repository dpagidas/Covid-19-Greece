package com.unipi.application.corona.dto;


import java.sql.Timestamp;
import java.util.Date;

public class VaccinationInfoLastDateReturn {

    private String areaGr;

    private Integer sumTotalDose2;

    private Integer sumTotalDose3;

    private Timestamp referenceDate;

    private Integer sumTotalDistinctPersons;

    private Integer sumTotalVaccinations;

    private Integer dayDiffTotalDose2;

    private Integer dayDiffTotalDose3;

    private Integer dayDiffTotalDistinctPersons;

    private Integer dayDiffTotalVaccinations;

    private Timestamp updatedAt;

    private Timestamp yesterdayDate;

    private String percentageSemiVaccinated;

    private String percentageFullyVaccinated;

    private String percentageThirdDoseVaccinated;

    public String getAreaGr() {
        return areaGr;
    }

    public void setAreaGr(String areaGr) {
        this.areaGr = areaGr;
    }

    public String getPercentageThirdDoseVaccinated() {
        return percentageThirdDoseVaccinated;
    }

    public void setPercentageThirdDoseVaccinated(String percentageThirdDoseVaccinated) {
        this.percentageThirdDoseVaccinated = percentageThirdDoseVaccinated;
    }

    public String getPercentageSemiVaccinated() {
        return percentageSemiVaccinated;
    }

    public void setPercentageSemiVaccinated(String percentageSemiVaccinated) {
        this.percentageSemiVaccinated = percentageSemiVaccinated;
    }

    public String getPercentageFullyVaccinated() {
        return percentageFullyVaccinated;
    }

    public void setPercentageFullyVaccinated(String percentageFullyVaccinated) {
        this.percentageFullyVaccinated = percentageFullyVaccinated;
    }

    public Timestamp getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Timestamp referenceDate) {
        this.referenceDate = referenceDate;
    }

    public Timestamp getYesterdayDate() {
        return yesterdayDate;
    }

    public void setYesterdayDate(Timestamp yesterdayDate) {
        this.yesterdayDate = yesterdayDate;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getSumTotalDose2() {
        return sumTotalDose2;
    }

    public void setSumTotalDose2(Integer sumTotalDose2) {
        this.sumTotalDose2 = sumTotalDose2;
    }

    public Integer getSumTotalDose3() {
        return sumTotalDose3;
    }

    public void setSumTotalDose3(Integer sumTotalDose3) {
        this.sumTotalDose3 = sumTotalDose3;
    }

    public Integer getSumTotalDistinctPersons() {
        return sumTotalDistinctPersons;
    }

    public void setSumTotalDistinctPersons(Integer sumTotalDistinctPersons) {
        this.sumTotalDistinctPersons = sumTotalDistinctPersons;
    }

    public Integer getSumTotalVaccinations() {
        return sumTotalVaccinations;
    }

    public void setSumTotalVaccinations(Integer sumTotalVaccinations) {
        this.sumTotalVaccinations = sumTotalVaccinations;
    }

    public Integer getDayDiffTotalDose2() {
        return dayDiffTotalDose2;
    }

    public void setDayDiffTotalDose2(Integer dayDiffTotalDose2) {
        this.dayDiffTotalDose2 = dayDiffTotalDose2;
    }

    public Integer getDayDiffTotalDose3() {
        return dayDiffTotalDose3;
    }

    public void setDayDiffTotalDose3(Integer dayDiffTotalDose3) {
        this.dayDiffTotalDose3 = dayDiffTotalDose3;
    }

    public Integer getDayDiffTotalDistinctPersons() {
        return dayDiffTotalDistinctPersons;
    }

    public void setDayDiffTotalDistinctPersons(Integer dayDiffTotalDistinctPersons) {
        this.dayDiffTotalDistinctPersons = dayDiffTotalDistinctPersons;
    }

    public Integer getDayDiffTotalVaccinations() {
        return dayDiffTotalVaccinations;
    }

    public void setDayDiffTotalVaccinations(Integer dayDiffTotalVaccinations) {
        this.dayDiffTotalVaccinations = dayDiffTotalVaccinations;
    }
}