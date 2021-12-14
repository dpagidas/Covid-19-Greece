package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.ConfirmedCasesApi.controller.ConfirmedCasesApiController;
import com.unipi.application.corona.apis.ConfirmedDeathsApi.controller.ConfirmedDeathsApiController;
import com.unipi.application.corona.dto.ConfirmedDeathsDtoReturn;
import com.unipi.application.corona.dto.TotalTestByDateDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.ConfirmedDeaths;
import com.unipi.application.corona.models.PlatformConfiguration;
import com.unipi.application.corona.models.TotalTests;
import com.unipi.application.corona.models.repository.ConfirmedCasesRepository;
import com.unipi.application.corona.models.repository.ConfirmedDeathsRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConfirmedDeathsServiceImpl implements ConfirmedDeathsService{

    @Autowired
    private ConfirmedDeathsApiController confirmedDeathsApiController;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private ConfirmedDeathsRepository confirmedDeathsRepository;


    @Override
    public List<ConfirmedDeaths> getConfirmedDeaths() {
        return confirmedDeathsRepository.findAll();
    }


    public ConfirmedDeathsDtoReturn getConfirmedDeathsLastDate() {
        ConfirmedDeathsDtoReturn confirmedDeathsDtoReturn = new ConfirmedDeathsDtoReturn();
        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        if(regionCaseHistoryLastUpdatedDate != null && genderAgeLastUpdatedDate != null && intensiveCareLastUpdateDate != null && confirmedCasesLastUpdate != null && totalTestsLastUpdate != null && confirmedDeathsLastUpdate != null){
            Timestamp earliest = Collections.min(Arrays.asList(regionCaseHistoryLastUpdatedDate, genderAgeLastUpdatedDate, intensiveCareLastUpdateDate, confirmedCasesLastUpdate, totalTestsLastUpdate, confirmedDeathsLastUpdate));
            List<ConfirmedDeaths> lastTwoDaysConfirmedDeaths = confirmedDeathsRepository.getLastTwoDatesByGivingDate(earliest);
            confirmedDeathsDtoReturn.setDate(lastTwoDaysConfirmedDeaths.get(0).getDate());
            confirmedDeathsDtoReturn.setAllDeaths(lastTwoDaysConfirmedDeaths.get(0).getDeaths());
            confirmedDeathsDtoReturn.setDeathsPerDay(lastTwoDaysConfirmedDeaths.get(0).getDeaths() - lastTwoDaysConfirmedDeaths.get(1).getDeaths());
            confirmedDeathsDtoReturn.setId(lastTwoDaysConfirmedDeaths.get(0).getId());
        }
        return confirmedDeathsDtoReturn;
    }

    public void fetchAndSaveConfirmedDeaths() {
        List<ConfirmedDeaths> confirmedDeathsList= new ArrayList<>();

        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE");
        if(platformConfiguration!= null){
            if(platformConfiguration.getDateValue() != null){
                confirmedDeathsList = confirmedDeathsApiController.getConfirmedDeaths();
                List<ConfirmedDeaths> confirmedDeathListFilteredByDate= new ArrayList<>();
                confirmedDeathListFilteredByDate =
                        confirmedDeathsList
                                .stream()
                                .filter( event -> event.getDate().compareTo(platformConfiguration.getDateValue()) > 0 )
                                .collect( Collectors.toList() )
                ;
                saveConfirmedDeaths(platformConfiguration, confirmedDeathListFilteredByDate);
            }else{
                confirmedDeathsList = confirmedDeathsApiController.getConfirmedDeaths();
                saveConfirmedDeaths(platformConfiguration, confirmedDeathsList);
            }
        }
    }

    private void saveConfirmedDeaths( PlatformConfiguration platformConfiguration, List<ConfirmedDeaths> confirmedDeathListFilteredByDate) {
        Collections.sort(confirmedDeathListFilteredByDate, new SortByDate());
        if (confirmedDeathListFilteredByDate.size() != 0) {
            confirmedDeathsRepository.saveAll(confirmedDeathListFilteredByDate);
            platformConfiguration.setDateValue(confirmedDeathListFilteredByDate.get(confirmedDeathListFilteredByDate.size() - 1).getDate());
            platformConfigurationRepository.save(platformConfiguration);
        }
    }


    static class SortByDate implements Comparator<ConfirmedDeaths> {
        @Override
        public int compare(ConfirmedDeaths a, ConfirmedDeaths b) {
            return a.getDate().compareTo(b.getDate());
        }
    }



}
