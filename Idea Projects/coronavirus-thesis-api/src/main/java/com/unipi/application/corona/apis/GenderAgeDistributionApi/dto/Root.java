package com.unipi.application.corona.apis.GenderAgeDistributionApi.dto;

import com.unipi.application.corona.models.Females;
import com.unipi.application.corona.models.Males;

import java.sql.Timestamp;

public class Root {

    public TotalAgeGenderDistributionDto total_age_gender_distribution;

    public Timestamp updated;

    public TotalAgeGenderDistributionDto getTotal_age_gender_distribution() {
        return total_age_gender_distribution;
    }

    public void setTotal_age_gender_distribution(TotalAgeGenderDistributionDto total_age_gender_distribution) {
        this.total_age_gender_distribution = total_age_gender_distribution;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
