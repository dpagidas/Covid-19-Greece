package com.unipi.application.corona.controller;

import com.unipi.application.corona.models.GenderAgeDistribution;
import com.unipi.application.corona.service.GenderAgeDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gender-age-distribution")
public class GenderAgeDistributionController {

    @Autowired
    private GenderAgeDistributionService genderAgeDistributionService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GenderAgeDistribution> fetchGenderAgeDistributionHistory(){
        GenderAgeDistribution genderAgeDistribution = genderAgeDistributionService.getGenderAgeDistributionHistory();
        return ResponseEntity.status(HttpStatus.OK).body(genderAgeDistribution);

    }
}
