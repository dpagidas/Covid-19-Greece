package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.AreasApi.controller.AreasApiController;
import com.unipi.application.corona.apis.AreasApi.dto.AreasDto;
import com.unipi.application.corona.apis.IntensiveCareApi.controller.IntensiveCareApiController;
import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionCasesHistoryDto;
import com.unipi.application.corona.dto.RegionCasesHistoryDtoReturn;
import com.unipi.application.corona.dto.TotalTestByDateDtoReturn;
import com.unipi.application.corona.models.*;
import com.unipi.application.corona.models.repository.AreasRepository;
import com.unipi.application.corona.models.repository.IntensiveCareRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IntensiveCareServiceImpl implements IntensiveCareService{

    @Autowired
    private IntensiveCareApiController intensiveCareApiController;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private IntensiveCareRepository intensiveCareRepository;


    @Override
    public List<IntensiveCare> getIntensiveCareCases() {
        return intensiveCareRepository.findAll();
    }


    public IntensiveCare getIntensiveCareCasesLastDate() {
        IntensiveCare lastDayIntensiveCare = new IntensiveCare();
        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        if(regionCaseHistoryLastUpdatedDate != null && genderAgeLastUpdatedDate != null && intensiveCareLastUpdateDate != null && confirmedCasesLastUpdate != null && totalTestsLastUpdate != null && confirmedDeathsLastUpdate != null){
            Timestamp earliest = Collections.min(Arrays.asList(regionCaseHistoryLastUpdatedDate, genderAgeLastUpdatedDate, intensiveCareLastUpdateDate, confirmedCasesLastUpdate, totalTestsLastUpdate, confirmedDeathsLastUpdate));
            lastDayIntensiveCare = intensiveCareRepository.getLastDateIntensiveCare(earliest);
        }
        return lastDayIntensiveCare;
    }

    public void fetchAndSaveIntensiveCareCases() {
        List<IntensiveCare> intensiveCareList= new ArrayList<>();

        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE");
        if(platformConfiguration!= null){
            if(platformConfiguration.getDateValue() != null){
                intensiveCareList = intensiveCareApiController.getIntensiveCare();
                List<IntensiveCare> intensiveCareListFilteredByDate= new ArrayList<>();
                intensiveCareListFilteredByDate =
                        intensiveCareList
                                .stream()
                                .filter( event -> event.getDate().compareTo(platformConfiguration.getDateValue()) > 0 )
                                .collect( Collectors.toList() )
                ;
                saveIntensiveCareHistory(intensiveCareList, platformConfiguration, intensiveCareListFilteredByDate);
            }else{
                intensiveCareList = intensiveCareApiController.getIntensiveCare();
                saveIntensiveCareHistory(intensiveCareList, platformConfiguration, intensiveCareList);
            }
        }
    }

    private void saveIntensiveCareHistory(List<IntensiveCare> intensiveCareList, PlatformConfiguration platformConfiguration, List<IntensiveCare> intensiveCareListFilteredByDate) {
        Collections.sort(intensiveCareListFilteredByDate, new SortByDate());
        if (intensiveCareListFilteredByDate.size() != 0) {
            intensiveCareRepository.saveAll(intensiveCareListFilteredByDate);
            platformConfiguration.setDateValue(intensiveCareListFilteredByDate.get(intensiveCareListFilteredByDate.size() - 1).getDate());
            platformConfigurationRepository.save(platformConfiguration);
        }
    }


    static class SortByDate implements Comparator<IntensiveCare> {
        @Override
        public int compare(IntensiveCare a, IntensiveCare b) {
            return a.getDate().compareTo(b.getDate());
        }
    }



}
