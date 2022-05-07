package com.unipi.application.corona.controller;

import com.unipi.application.corona.dto.CasesByRegionDtoReturn;
import com.unipi.application.corona.dto.RegionCasesHistoryDtoReturn;
import com.unipi.application.corona.dto.VaccinationInfoLastDateReturn;
import com.unipi.application.corona.models.Covid19News;
import com.unipi.application.corona.models.RegionCasesHistory;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import com.unipi.application.corona.service.Covid19NewsInfoService;
import com.unipi.application.corona.service.CovidCasesPerRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region-cases-history")
public class RegionCasesHistoryController {

    @Autowired
    private CovidCasesPerRegionService covidCasesPerRegionService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RegionCasesHistoryDtoReturn>> fetchAllVaccinationInfo(){
        List<RegionCasesHistoryDtoReturn> regionCasesHistoryList = covidCasesPerRegionService.getCovidCasesPerRegion();
        return ResponseEntity.status(HttpStatus.OK).body(regionCasesHistoryList);
    }

    @PostMapping(value = "/getAllCasesListByRegion", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CasesByRegionDtoReturn>> fetchAllVaccinationListByRegion(@RequestBody SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults){
        List<CasesByRegionDtoReturn> casesByRegionDtoReturns = covidCasesPerRegionService.getListOfCasesByRegion(searchRequestListDateVaccinationsResults);
        return ResponseEntity.status(HttpStatus.OK).body(casesByRegionDtoReturns);
    }
}
