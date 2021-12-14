package com.unipi.application.corona.apis.TotalTestsApi.controller;

import com.unipi.application.corona.apis.AreasApi.dto.AreasDto;
import com.unipi.application.corona.apis.AreasApi.service.AreasApiService;
import com.unipi.application.corona.apis.IntensiveCareApi.service.IntensiveCareApiService;
import com.unipi.application.corona.apis.TotalTestsApi.service.TotalTestsApiService;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.models.TotalTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TotalTestsApiController {

    @Autowired
    private TotalTestsApiService totalTestsApiService;

    public List<TotalTests> getTotalTests() {
        return totalTestsApiService.getTotalTests();
    }
}
