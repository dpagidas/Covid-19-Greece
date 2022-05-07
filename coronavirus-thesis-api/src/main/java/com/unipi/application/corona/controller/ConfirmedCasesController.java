package com.unipi.application.corona.controller;

import com.unipi.application.corona.dto.CasesListByDateDtoReturn;
import com.unipi.application.corona.dto.ConfirmedCasesDtoReturn;
import com.unipi.application.corona.dto.VaccinationInfoLastDateReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import com.unipi.application.corona.service.ConfirmedCasesService;
import com.unipi.application.corona.service.IntensiveCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/confirmed-cases")
public class ConfirmedCasesController {

    @Autowired
    private ConfirmedCasesService confirmedCasesService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ConfirmedCases>> fetchAllAreas(){
        List<ConfirmedCases> confirmedCasesList = confirmedCasesService.getConfirmedCases();
        return ResponseEntity.status(HttpStatus.OK).body(confirmedCasesList);

    }

    @GetMapping(value = "/lastDateConfirmedCases", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ConfirmedCasesDtoReturn> fetchLastDateConfirmedCases(){
        ConfirmedCasesDtoReturn lastDateConfirmedCasesDto = confirmedCasesService.getLastDateConfirmedCases();
        return ResponseEntity.status(HttpStatus.OK).body(lastDateConfirmedCasesDto);

    }

    @PostMapping(value = "/getListCasesDataByDate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CasesListByDateDtoReturn>> fetchAllCasesListByDate(@RequestBody SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults){
        List<CasesListByDateDtoReturn> casesListByDateDtoReturns = confirmedCasesService.getListPerDayCasesData(searchRequestListDateVaccinationsResults);
        return ResponseEntity.status(HttpStatus.OK).body(casesListByDateDtoReturns);

    }
}
