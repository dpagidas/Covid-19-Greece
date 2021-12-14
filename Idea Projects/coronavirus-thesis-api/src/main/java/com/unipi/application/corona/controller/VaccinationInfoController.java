package com.unipi.application.corona.controller;

import com.unipi.application.corona.dto.SearchResponseModel;
import com.unipi.application.corona.dto.VaccinationInfoLastDateReturn;
import com.unipi.application.corona.dto.VaccinationsHistoryDtoReturn;
import com.unipi.application.corona.models.VaccinationInfo;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import com.unipi.application.corona.service.VaccinationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vaccination")
public class VaccinationInfoController {

    @Autowired
    private VaccinationInfoService vaccinationInfoService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<VaccinationsHistoryDtoReturn>> fetchAllVaccinationInfo(){
        List<VaccinationsHistoryDtoReturn> vaccinationInfos = vaccinationInfoService.getVaccinationHistory();
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationInfos);

    }

    @GetMapping(value = "/vaccinationLastDayData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity <VaccinationInfoLastDateReturn> fetchLastDateVaccinationData(){
        VaccinationInfoLastDateReturn vaccinationInfoLastDateReturn = vaccinationInfoService.getVaccinationDataByDate();
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationInfoLastDateReturn);
    }

    @PostMapping(value = "/getAllVaccinationResultsByDate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<VaccinationInfoLastDateReturn>> fetchAllVaccinationResultsByDate(@RequestBody SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults){
//        Timestamp fromDateTimestamp = null;
//        Timestamp toDateTimestamp = null;
//        if(fromDate != null){
//            fromDateTimestamp = Timestamp.valueOf(fromDate);
//        }
//        if(toDate != null){
//            toDateTimestamp = Timestamp.valueOf(toDate);
//        }
        List<VaccinationInfoLastDateReturn> vaccinationInfoLastDateReturnsList = vaccinationInfoService.getListOfDatesAndTotalVaccinationResults(searchRequestListDateVaccinationsResults);
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationInfoLastDateReturnsList);

    }


    @PostMapping(value = "/getAllVaccinationListByRegion", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<VaccinationInfoLastDateReturn>> fetchAllVaccinationListByRegion(@RequestBody SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults){
//        Timestamp fromDateTimestamp = null;
//        Timestamp toDateTimestamp = null;
//        if(fromDate != null){
//            fromDateTimestamp = Timestamp.valueOf(fromDate);
//        }
//        if(toDate != null){
//            toDateTimestamp = Timestamp.valueOf(toDate);
//        }
        List<VaccinationInfoLastDateReturn> vaccinationInfoLastDateReturnsList = vaccinationInfoService.getListOfVaccinationPerRegion(searchRequestListDateVaccinationsResults);
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationInfoLastDateReturnsList);

    }
}

