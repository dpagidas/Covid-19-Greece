package com.unipi.application.corona.controller;

import com.unipi.application.corona.dto.VaccinationsHistoryDtoReturn;
import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.service.AreasService;
import com.unipi.application.corona.service.VaccinationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreasController {

    @Autowired
    private AreasService areasService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Areas>> fetchAllAreas(){
        List<Areas> areas = areasService.getAreas();
        return ResponseEntity.status(HttpStatus.OK).body(areas);

    }
}
