package com.unipi.application.corona.apis.GenderAgeDistributionApi.controller;

import com.unipi.application.corona.apis.AreasApi.dto.AreasDto;
import com.unipi.application.corona.apis.AreasApi.service.AreasApiService;
import com.unipi.application.corona.apis.GenderAgeDistributionApi.dto.Root;
import com.unipi.application.corona.apis.GenderAgeDistributionApi.service.GenderAgeDistributionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GenderAgeDistributionApiController {

    @Autowired
    private GenderAgeDistributionApiService genderAgeDistributionApiService;

    public Root getAgeDistributionHistory() {
        return genderAgeDistributionApiService.getAgeDistributionHistory();
    }
}
