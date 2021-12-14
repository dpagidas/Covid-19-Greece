package com.unipi.application.corona.service;

import com.unipi.application.corona.models.GenderAgeDistribution;
import org.springframework.stereotype.Service;

@Service
public interface GenderAgeDistributionService {

    GenderAgeDistribution getGenderAgeDistributionHistory();
}
