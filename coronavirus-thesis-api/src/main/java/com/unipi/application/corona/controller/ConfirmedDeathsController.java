package com.unipi.application.corona.controller;

import com.unipi.application.corona.dto.ConfirmedDeathsDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.ConfirmedDeaths;
import com.unipi.application.corona.service.ConfirmedCasesService;
import com.unipi.application.corona.service.ConfirmedDeathsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/confirmed-deaths")
public class ConfirmedDeathsController {

    @Autowired
    private ConfirmedDeathsService confirmedDeathsService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ConfirmedDeaths>> fetchAllAreas(){
        List<ConfirmedDeaths> confirmedDeathsList = confirmedDeathsService.getConfirmedDeaths();
        return ResponseEntity.status(HttpStatus.OK).body(confirmedDeathsList);

    }


    @GetMapping(value = "/lastDayConfirmedDeaths", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ConfirmedDeathsDtoReturn> fetchLastDayConfirmedDeaths(){
        ConfirmedDeathsDtoReturn lastDayConfirmedDeaths = confirmedDeathsService.getConfirmedDeathsLastDate();
        return ResponseEntity.status(HttpStatus.OK).body(lastDayConfirmedDeaths);

    }
}
