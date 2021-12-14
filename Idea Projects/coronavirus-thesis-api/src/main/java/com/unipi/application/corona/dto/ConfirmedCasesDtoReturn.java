package com.unipi.application.corona.dto;

import java.sql.Timestamp;

public class ConfirmedCasesDtoReturn {

    private Long id;

    public Integer totalCases;

    public Integer lastDateCases;

    public Timestamp date;

    public Double positivityIndex;

    public Integer averageCasesLast7Days;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    public Integer getLastDateCases() {
        return lastDateCases;
    }

    public void setLastDateCases(Integer lastDateCases) {
        this.lastDateCases = lastDateCases;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getPositivityIndex() {
        return positivityIndex;
    }

    public void setPositivityIndex(Double positivityIndex) {
        this.positivityIndex = positivityIndex;
    }

    public Integer getAverageCasesLast7Days() {
        return averageCasesLast7Days;
    }

    public void setAverageCasesLast7Days(Integer averageCasesLast7Days) {
        this.averageCasesLast7Days = averageCasesLast7Days;
    }
}