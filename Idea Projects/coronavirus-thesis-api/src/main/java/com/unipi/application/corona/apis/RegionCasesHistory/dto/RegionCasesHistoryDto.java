package com.unipi.application.corona.apis.RegionCasesHistory.dto;

import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionDto;

import java.sql.Timestamp;
import java.util.List;

public class RegionCasesHistoryDto {

    public Timestamp date;

    public List<RegionDto> regions;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<RegionDto> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionDto> regions) {
        this.regions = regions;
    }
}
