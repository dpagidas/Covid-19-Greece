package com.unipi.application.corona.dto;

import java.sql.Timestamp;
import java.util.List;

public class RegionCasesHistoryDtoReturn {

    public Timestamp date;

    public List<RegionDtoReturn> regions;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<RegionDtoReturn> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionDtoReturn> regions) {
        this.regions = regions;
    }
}
