package com.unipi.application.corona.apis.ConfirmedCasesApi.controller;

import com.unipi.application.corona.apis.AreasApi.dto.AreasDto;
import com.unipi.application.corona.apis.AreasApi.service.AreasApiService;
import com.unipi.application.corona.apis.ConfirmedCasesApi.service.ConfirmedCasesApiService;
import com.unipi.application.corona.apis.IntensiveCareApi.service.IntensiveCareApiService;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.IntensiveCare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ConfirmedCasesApiController {

    @Autowired
    private ConfirmedCasesApiService confirmedCasesApiService;

    public List<ConfirmedCases> getConfirmedCases() {
        return confirmedCasesApiService.getConfirmedCases();
    }
}
