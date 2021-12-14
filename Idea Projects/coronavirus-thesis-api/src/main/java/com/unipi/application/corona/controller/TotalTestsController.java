package com.unipi.application.corona.controller;

import com.unipi.application.corona.dto.TotalTestByDateDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.models.TotalTests;
import com.unipi.application.corona.service.IntensiveCareService;
import com.unipi.application.corona.service.TotalTestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/total-tests")
public class TotalTestsController {

    @Autowired
    private TotalTestsService totalTestsService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TotalTests>> fetchAllAreas(){
        List<TotalTests> intensiveCareList = totalTestsService.getTotalTests();
        return ResponseEntity.status(HttpStatus.OK).body(intensiveCareList);

    }


    @GetMapping(value = "/lastDayTotalTests", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TotalTestByDateDtoReturn> fetchLastDateTests(){
        TotalTestByDateDtoReturn confirmedCasesList = totalTestsService.getTotalTestByLastDate();
        return ResponseEntity.status(HttpStatus.OK).body(confirmedCasesList);

    }
}
