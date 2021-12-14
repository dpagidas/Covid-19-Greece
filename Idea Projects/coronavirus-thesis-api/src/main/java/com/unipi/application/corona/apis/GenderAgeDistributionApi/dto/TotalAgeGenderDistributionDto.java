package com.unipi.application.corona.apis.GenderAgeDistributionApi.dto;

import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionDto;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.List;

public class TotalAgeGenderDistributionDto {

   public Males males;

   public Females females;

    public Males getMales() {
        return males;
    }

    public void setMales(Males males) {
        this.males = males;
    }

    public Females getFemales() {
        return females;
    }

    public void setFemales(Females females) {
        this.females = females;
    }
}
