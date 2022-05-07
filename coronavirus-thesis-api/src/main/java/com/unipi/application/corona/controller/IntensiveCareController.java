package com.unipi.application.corona.controller;

import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.service.AreasService;
import com.unipi.application.corona.service.IntensiveCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/intensive-care")
public class IntensiveCareController {

    @Autowired
    private IntensiveCareService intensiveCareService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<IntensiveCare>> fetchAllAreas(){
        List<IntensiveCare> intensiveCareList = intensiveCareService.getIntensiveCareCases();
        return ResponseEntity.status(HttpStatus.OK).body(intensiveCareList);
    }

    @GetMapping(value = "/lastDayIntensiveCare", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<IntensiveCare> fetchLastDayIntensiveCare(){
        IntensiveCare lastDayIntensiveCare = intensiveCareService.getIntensiveCareCasesLastDate();
        return ResponseEntity.status(HttpStatus.OK).body(lastDayIntensiveCare);
    }
}
