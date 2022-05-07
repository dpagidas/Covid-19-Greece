package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.AreasApi.controller.AreasApiController;
import com.unipi.application.corona.apis.AreasApi.dto.AreasDto;
import com.unipi.application.corona.apis.VaccinationStatisticsApi.controller.DataGovController;
import com.unipi.application.corona.apis.VaccinationStatisticsApi.dto.VaccinationInfoDTO;
import com.unipi.application.corona.controller.AreasController;
import com.unipi.application.corona.dto.VaccinationsHistoryDtoReturn;
import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.Covid19News;
import com.unipi.application.corona.models.PlatformConfiguration;
import com.unipi.application.corona.models.VaccinationInfo;
import com.unipi.application.corona.models.repository.AreasRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import com.unipi.application.corona.models.repository.VaccinationInfoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AreasServiceImpl implements AreasService{

    @Autowired
    private AreasApiController areasController;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private AreasRepository areasRepository;


    public List<Areas> getAreas() {
        List<AreasDto> areas = new ArrayList<>();
        areas = areasController.getAreas();
        areas.forEach(x -> {
            if(areasRepository.findByAreaGr(x.getArea_gr()) == null){
                Areas area =  new Areas();
                area.setAreaGr(x.getArea_gr());
                area.setAreaEn(x.getArea_en());
                area.setRegionGr(x.getRegion_gr());
                area.setRegionEn(x.getRegion_en());
                area.setPopulation(x.getPopulation());
                area.setCases_per_100000_people(x.getCases_per_100000_people());
                area.setTotal_cases(x.getTotal_cases());
                area.setGeo_department_en(x.getGeo_department_en());
                area.setGeo_department_gr(x.getGeo_department_gr());
                area.setLongtitude(x.getLongtitude());
                area.setLatitude(x.getLatitude());
                area.setLast_updated_at(x.getLast_updated_at());
                areasRepository.save(area);
            };
        });
        return areasRepository.findAll();
    }



}
