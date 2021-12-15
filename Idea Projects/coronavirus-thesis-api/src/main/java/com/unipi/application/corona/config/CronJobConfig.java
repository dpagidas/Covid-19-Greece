package com.unipi.application.corona.config;

import com.unipi.application.corona.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CronJobConfig {

    @Autowired
    private CovidCasesPerRegionImpl covidCasesPerRegion;

    @Autowired
    private VaccinationInfoServiceImpl vaccinationInfoService;

    @Autowired
    private Covid19NewsInfoServiceImpl covid19NewsInfoService;

    @Autowired
    private GenderAgeDistributionImpl genderAgeDistributionService;

    @Autowired
    private IntensiveCareServiceImpl intensiveCareService;

    @Autowired
    private ConfirmedCasesServiceImpl confirmedCasesService;

    @Autowired
    private TotalTestsServiceImpl totalTestsService;

    @Autowired
    private ConfirmedDeathsServiceImpl confirmedDeathsService;


//    @Scheduled(cron = "0 0/1 * * * *")

    @Scheduled(cron = "0 00 04 * * ?")
    public void scheduleFixedDelayTask() {
        covidCasesPerRegion.fetchAndSaveCovidCaseRegionHistory();
        vaccinationInfoService.fetchAndSaveVaccinationHistory();
        covid19NewsInfoService.fetchAndSaveCovid19News();
        genderAgeDistributionService.fetchAndSaveGenderAgeDistributionHistory();
        intensiveCareService.fetchAndSaveIntensiveCareCases();
        confirmedCasesService.fetchAndSaveConfirmedCases();
        totalTestsService.fetchAndSaveTotalTests();
        confirmedDeathsService.fetchAndSaveConfirmedDeaths();
    }


    @Scheduled(cron = "0 00 16 * * ?")
    public void scheduleTask() {
        covidCasesPerRegion.fetchAndSaveCovidCaseRegionHistory();
        vaccinationInfoService.fetchAndSaveVaccinationHistory();
        covid19NewsInfoService.fetchAndSaveCovid19News();
        genderAgeDistributionService.fetchAndSaveGenderAgeDistributionHistory();
        intensiveCareService.fetchAndSaveIntensiveCareCases();
        confirmedCasesService.fetchAndSaveConfirmedCases();
        totalTestsService.fetchAndSaveTotalTests();
        confirmedDeathsService.fetchAndSaveConfirmedDeaths();
    }

    @Scheduled(cron = "0 0/30 * * * *")
    public void scheduleTaskCovid19News() {
        covid19NewsInfoService.fetchAndSaveCovid19News();
    }
}
