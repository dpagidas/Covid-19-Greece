package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.ConfirmedCasesApi.controller.ConfirmedCasesApiController;
import com.unipi.application.corona.apis.TotalTestsApi.controller.TotalTestsApiController;
import com.unipi.application.corona.dto.TotalTestByDateDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.PlatformConfiguration;
import com.unipi.application.corona.models.TotalTests;
import com.unipi.application.corona.models.repository.ConfirmedCasesRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import com.unipi.application.corona.models.repository.TotalTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TotalTestsServiceImpl implements TotalTestsService{

    @Autowired
    private TotalTestsApiController totalTestsApiController;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private TotalTestsRepository totalTestsRepository;


    @Override
    public List<TotalTests> getTotalTests() {
        return totalTestsRepository.findAll();
    }

    public TotalTestByDateDtoReturn getTotalTestByLastDate() {
        TotalTestByDateDtoReturn totalTestByDateDtoReturn = new TotalTestByDateDtoReturn();
        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        if(regionCaseHistoryLastUpdatedDate != null && genderAgeLastUpdatedDate != null && intensiveCareLastUpdateDate != null && confirmedCasesLastUpdate != null && totalTestsLastUpdate != null && confirmedDeathsLastUpdate != null){
            Timestamp earliest = Collections.min(Arrays.asList(regionCaseHistoryLastUpdatedDate, genderAgeLastUpdatedDate, intensiveCareLastUpdateDate, confirmedCasesLastUpdate, totalTestsLastUpdate, confirmedDeathsLastUpdate));
            List<TotalTests> lastTwoDaysTest = totalTestsRepository.getLastTwoDatesByGivingDate(earliest);
            totalTestByDateDtoReturn.setDate(lastTwoDaysTest.get(0).getDate());
            totalTestByDateDtoReturn.setTests(lastTwoDaysTest.get(0).getTests() - lastTwoDaysTest.get(1).getTests());
            totalTestByDateDtoReturn.setRapidTests(lastTwoDaysTest.get(0).getRapidTests() - lastTwoDaysTest.get(1).getRapidTests());
            totalTestByDateDtoReturn.setAllTests(lastTwoDaysTest.get(0).getTests() + lastTwoDaysTest.get(1).getRapidTests());
            totalTestByDateDtoReturn.setId(lastTwoDaysTest.get(0).getId());
        }
        return totalTestByDateDtoReturn;
    }

    public void fetchAndSaveTotalTests() {
        List<TotalTests> totalTestsList= new ArrayList<>();

        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE");
        if(platformConfiguration!= null){
            if(platformConfiguration.getDateValue() != null){
                totalTestsList = totalTestsApiController.getTotalTests();
                List<TotalTests> totalTestListFilteredByDate= new ArrayList<>();
                totalTestListFilteredByDate =
                        totalTestsList
                                .stream()
                                .filter( event -> event.getDate().compareTo(platformConfiguration.getDateValue()) > 0 )
                                .collect( Collectors.toList() )
                ;
                saveTotalTests(platformConfiguration, totalTestListFilteredByDate);
            }else{
                totalTestsList = totalTestsApiController.getTotalTests();
                saveTotalTests(platformConfiguration, totalTestsList);
            }
        }
    }

    private void saveTotalTests( PlatformConfiguration platformConfiguration, List<TotalTests> totalTestListFilteredByDate) {
        Collections.sort(totalTestListFilteredByDate, new SortByDate());
        if (totalTestListFilteredByDate.size() != 0) {
            totalTestsRepository.saveAll(totalTestListFilteredByDate);
            platformConfiguration.setDateValue(totalTestListFilteredByDate.get(totalTestListFilteredByDate.size() - 1).getDate());
            platformConfigurationRepository.save(platformConfiguration);
        }
    }


    static class SortByDate implements Comparator<TotalTests> {
        @Override
        public int compare(TotalTests a, TotalTests b) {
            return a.getDate().compareTo(b.getDate());
        }
    }



}
