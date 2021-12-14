package com.unipi.application.corona.controller;

import com.unipi.application.corona.dto.SearchResponseModel;
import com.unipi.application.corona.models.Covid19News;
import com.unipi.application.corona.models.VaccinationInfo;
import com.unipi.application.corona.service.Covid19NewsInfoService;
import com.unipi.application.corona.service.VaccinationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/covid19News")
public class Covid19NewsController {

    @Autowired
    private Covid19NewsInfoService covid19NewsInfoService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Covid19News>> fetchAllVaccinationInfo(){
        List<Covid19News> vaccinationInfos = covid19NewsInfoService.getCovid19News();
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationInfos);

    }

    @PostMapping(value = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SearchResponseModel> fetchCovid19NewsPaged(@RequestParam int page , @RequestParam int offset){
        SearchResponseModel covid19News = covid19NewsInfoService.getPagedCovid19News(page , offset);
        return ResponseEntity.status(HttpStatus.OK).body(covid19News);

    }
}
