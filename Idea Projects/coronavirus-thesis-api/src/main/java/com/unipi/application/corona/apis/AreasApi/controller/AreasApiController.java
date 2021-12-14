package com.unipi.application.corona.apis.AreasApi.controller;

import com.unipi.application.corona.apis.AreasApi.dto.AreasDto;
import com.unipi.application.corona.apis.AreasApi.service.AreasApiService;
import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.Covid19News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AreasApiController {

    @Autowired
    private AreasApiService areasApiService;

    public List<AreasDto> getAreas() {
        return areasApiService.getAreas();
    }
}
