package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.ConfirmedCasesApi.controller.ConfirmedCasesApiController;
import com.unipi.application.corona.apis.IntensiveCareApi.controller.IntensiveCareApiController;
import com.unipi.application.corona.dto.CasesListByDateDtoReturn;
import com.unipi.application.corona.dto.ConfirmedCasesDtoReturn;
import com.unipi.application.corona.dto.TotalTestByDateDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.models.PlatformConfiguration;
import com.unipi.application.corona.models.TotalTests;
import com.unipi.application.corona.models.projection.CasesListByDate;
import com.unipi.application.corona.models.projection.VaccinationInfoLastDate;
import com.unipi.application.corona.models.repository.ConfirmedCasesRepository;
import com.unipi.application.corona.models.repository.IntensiveCareRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import com.unipi.application.corona.models.repository.TotalTestsRepository;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConfirmedCasesServiceImpl implements ConfirmedCasesService{

    @Autowired
    private ConfirmedCasesApiController confirmedCasesApiController;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private ConfirmedCasesRepository confirmedCasesRepository;

    @Autowired
    private TotalTestsRepository totalTestsRepository;


    @Override
    public List<ConfirmedCases> getConfirmedCases() {
        return confirmedCasesRepository.findAll();
    }


    public ConfirmedCasesDtoReturn getLastDateConfirmedCases() {
        ConfirmedCasesDtoReturn confirmedCasesDtoReturn = new ConfirmedCasesDtoReturn();
        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        if(regionCaseHistoryLastUpdatedDate != null && genderAgeLastUpdatedDate != null && intensiveCareLastUpdateDate != null && confirmedCasesLastUpdate != null && totalTestsLastUpdate != null && confirmedDeathsLastUpdate != null){
            Timestamp earliest = Collections.min(Arrays.asList(regionCaseHistoryLastUpdatedDate, genderAgeLastUpdatedDate, intensiveCareLastUpdateDate, confirmedCasesLastUpdate, totalTestsLastUpdate, confirmedDeathsLastUpdate));
            List<ConfirmedCases> lastTwoDaysCases = confirmedCasesRepository.getLastTwoDatesByGivingDate(earliest);
            List<TotalTests> lastTwoDaysTest = totalTestsRepository.getLastTwoDatesByGivingDate(earliest);
            List<ConfirmedCases> last7DaysCases = confirmedCasesRepository.getLast7Days();
            confirmedCasesDtoReturn.setDate(lastTwoDaysCases.get(0).getDate());
            confirmedCasesDtoReturn.setLastDateCases(lastTwoDaysCases.get(0).getConfirmed() - lastTwoDaysCases.get(1).getConfirmed());
            confirmedCasesDtoReturn.setTotalCases(lastTwoDaysCases.get(0).getConfirmed());
            Integer lastTwoDaysCasesNumber = lastTwoDaysCases.get(0).getConfirmed() - lastTwoDaysCases.get(1).getConfirmed();
            Integer lastTwoDaysTestsNumber = (lastTwoDaysTest.get(0).getTests() - lastTwoDaysTest.get(1).getTests()) + (lastTwoDaysTest.get(0).getRapidTests() - lastTwoDaysTest.get(1).getRapidTests());
            Double positivityIndex = (double)lastTwoDaysCasesNumber/lastTwoDaysTestsNumber * 100;
            confirmedCasesDtoReturn.setPositivityIndex(Math.floor(positivityIndex * 100) / 100);
            confirmedCasesDtoReturn.setAverageCasesLast7Days((last7DaysCases.get(0).getConfirmed() - last7DaysCases.get(6).getConfirmed())/ 7);
            confirmedCasesDtoReturn.setId(lastTwoDaysCases.get(0).getId());
        }
        return confirmedCasesDtoReturn;
    }


    public List<CasesListByDateDtoReturn> getListPerDayCasesData(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults) {
        List<CasesListByDateDtoReturn> casesListByDateDtoReturnsList = new ArrayList<>();
        Timestamp yesterdayFromDate  = null;
        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        if(regionCaseHistoryLastUpdatedDate != null && genderAgeLastUpdatedDate != null && intensiveCareLastUpdateDate != null && confirmedCasesLastUpdate != null && totalTestsLastUpdate != null && confirmedDeathsLastUpdate != null){
            Timestamp earliest = Collections.min(Arrays.asList(regionCaseHistoryLastUpdatedDate, genderAgeLastUpdatedDate, intensiveCareLastUpdateDate, confirmedCasesLastUpdate, totalTestsLastUpdate, confirmedDeathsLastUpdate));
            ZonedDateTime zonedDateTime = earliest.toInstant().atZone(ZoneId.of("UTC"));
            Timestamp yesterday = Timestamp.from(zonedDateTime.minus(1, ChronoUnit.DAYS).toInstant());
            if(searchRequestListDateVaccinationsResults.getFromDate() == null && searchRequestListDateVaccinationsResults.getToDate() != null){
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeFromDate = LocalDateTime.from(formatDateTime.parse("2018-12-28T00:00:00.000Z"));
                Timestamp timestampFromDate = Timestamp.valueOf(localDateTimeFromDate);
                searchRequestListDateVaccinationsResults.setFromDate(timestampFromDate);
            }else if(searchRequestListDateVaccinationsResults.getToDate() == null && searchRequestListDateVaccinationsResults.getFromDate() != null){
                ZonedDateTime zonedDateTimeFrom = searchRequestListDateVaccinationsResults.getFromDate().toInstant().atZone(ZoneId.of("UTC"));
                yesterdayFromDate = Timestamp.from(zonedDateTimeFrom.minus(1, ChronoUnit.DAYS).toInstant());
                String timestampFormatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(earliest);
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeEarliest = LocalDateTime.from(formatDateTime.parse(timestampFormatted));
                Timestamp timestampToDate = Timestamp.valueOf(localDateTimeEarliest);
                searchRequestListDateVaccinationsResults.setToDate(timestampToDate);
                searchRequestListDateVaccinationsResults.setFromDate(yesterdayFromDate);
            }else if(searchRequestListDateVaccinationsResults.getToDate() == null && searchRequestListDateVaccinationsResults.getFromDate() == null){
                String timestampFormatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(earliest);
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeEarliest = LocalDateTime.from(formatDateTime.parse(timestampFormatted));
                LocalDateTime localDateTimeFromDate = LocalDateTime.from(formatDateTime.parse("2018-12-28T00:00:00.000Z"));
                Timestamp timestamp = Timestamp.valueOf(localDateTimeEarliest);
                Timestamp setFromDate = Timestamp.valueOf(localDateTimeFromDate);
                searchRequestListDateVaccinationsResults.setToDate(timestamp);
                searchRequestListDateVaccinationsResults.setFromDate(setFromDate);
            }else if(searchRequestListDateVaccinationsResults.getToDate() != null && searchRequestListDateVaccinationsResults.getFromDate() != null){
                ZonedDateTime zonedDateTimeFrom = searchRequestListDateVaccinationsResults.getFromDate().toInstant().atZone(ZoneId.of("UTC"));
                yesterdayFromDate = Timestamp.from(zonedDateTimeFrom.minus(1, ChronoUnit.DAYS).toInstant());
                searchRequestListDateVaccinationsResults.setFromDate(yesterdayFromDate);
            }

            List<CasesListByDate> casesListByDates = confirmedCasesRepository.getListCasesDataByDate(searchRequestListDateVaccinationsResults.getFromDate(),searchRequestListDateVaccinationsResults.getToDate());

            casesListByDates.forEach(x->{
                CasesListByDateDtoReturn casesListByDateDtoReturn = new CasesListByDateDtoReturn();
                casesListByDateDtoReturn.setConfirmedCases(x.getConfirmedCases());
                casesListByDateDtoReturn.setIntensiveCare(x.getIntensiveCare());
                casesListByDateDtoReturn.setConfirmedDeaths(x.getConfirmedDeaths());
                casesListByDateDtoReturn.setDate(x.getDate());


                Integer lastTwoDaysTestsNumber = x.getTests() + x.getRapidTests();
                if(lastTwoDaysTestsNumber != 0){
                    Double positivityIndex = (double)x.getConfirmedCases()/lastTwoDaysTestsNumber * 100;
                    casesListByDateDtoReturn.setPositivityIndex(Math.floor(positivityIndex * 100) / 100);
                }
                casesListByDateDtoReturnsList.add(casesListByDateDtoReturn);

            });
            if(casesListByDateDtoReturnsList.size() > 0 && yesterdayFromDate != null){
                casesListByDateDtoReturnsList.remove(0);
            }
        }
        return casesListByDateDtoReturnsList;
    }

    public void fetchAndSaveConfirmedCases() {
        List<ConfirmedCases> confirmedCasesList= new ArrayList<>();

        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE");
        if(platformConfiguration!= null){
            if(platformConfiguration.getDateValue() != null){
                confirmedCasesList = confirmedCasesApiController.getConfirmedCases();
                List<ConfirmedCases> confirmedCaseListFilteredByDate= new ArrayList<>();
                confirmedCaseListFilteredByDate =
                        confirmedCasesList
                                .stream()
                                .filter( event -> event.getDate().compareTo(platformConfiguration.getDateValue()) > 0 )
                                .collect( Collectors.toList() )
                ;
                saveConfirmedCasesHistory(platformConfiguration, confirmedCaseListFilteredByDate);
            }else{
                confirmedCasesList = confirmedCasesApiController.getConfirmedCases();
                saveConfirmedCasesHistory(platformConfiguration, confirmedCasesList);
            }
        }
    }

    private void saveConfirmedCasesHistory( PlatformConfiguration platformConfiguration, List<ConfirmedCases> confirmedCaseListFilteredByDate) {
        Collections.sort(confirmedCaseListFilteredByDate, new SortByDate());
        if (confirmedCaseListFilteredByDate.size() != 0) {
            confirmedCasesRepository.saveAll(confirmedCaseListFilteredByDate);
            platformConfiguration.setDateValue(confirmedCaseListFilteredByDate.get(confirmedCaseListFilteredByDate.size() - 1).getDate());
            platformConfigurationRepository.save(platformConfiguration);
        }
    }


    static class SortByDate implements Comparator<ConfirmedCases> {
        @Override
        public int compare(ConfirmedCases a, ConfirmedCases b) {
            return a.getDate().compareTo(b.getDate());
        }
    }



}
