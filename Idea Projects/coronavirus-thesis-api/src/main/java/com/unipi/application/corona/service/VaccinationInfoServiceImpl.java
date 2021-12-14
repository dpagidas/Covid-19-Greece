package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionCasesHistoryDto;
import com.unipi.application.corona.apis.VaccinationStatisticsApi.controller.DataGovController;
import com.unipi.application.corona.apis.VaccinationStatisticsApi.dto.VaccinationInfoDTO;
import com.unipi.application.corona.dto.RegionCasesHistoryDtoReturn;
import com.unipi.application.corona.dto.VaccinationInfoLastDateReturn;
import com.unipi.application.corona.dto.VaccinationsHistoryDtoReturn;
import com.unipi.application.corona.models.*;
import com.unipi.application.corona.models.projection.VaccinationInfoLastDate;
import com.unipi.application.corona.models.repository.AreasRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import com.unipi.application.corona.models.repository.VaccinationInfoRepository;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import liquibase.pro.packaged.V;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VaccinationInfoServiceImpl implements VaccinationInfoService{

    @Autowired
    private DataGovController dataGovController;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private VaccinationInfoRepository vaccinationInfoRepository;

    @Autowired
    private AreasRepository areasRepository;

    @Override
    public List<VaccinationsHistoryDtoReturn> getVaccinationHistory() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<VaccinationsHistoryDtoReturn> vaccinationsHistoryDtoReturns = new ArrayList<>();
        vaccinationInfoRepository.findAll().forEach(x -> {
            vaccinationsHistoryDtoReturns.add(modelMapper.map(x, VaccinationsHistoryDtoReturn.class));
        });
        return vaccinationsHistoryDtoReturns;
    }


    @Override
    public List<VaccinationInfoLastDateReturn> getListOfVaccinationPerRegion(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults) {
        List<VaccinationInfoLastDateReturn> vaccinationsHistoryDtoReturnList = new ArrayList<>();

//        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
//        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
//        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
//        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
//        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
//        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        Timestamp vaccinationLastUpdate =  platformConfigurationRepository.findByConfigurationVariable("VACCINATION_INFO_LAST_UPDATE").getDateValue();

        if(vaccinationLastUpdate != null){
            Timestamp earliest = vaccinationLastUpdate;
//            ZonedDateTime zonedDateTime = earliest.toInstant().atZone(ZoneId.of("UTC"));
//            Timestamp yesterday = Timestamp.from(zonedDateTime.minus(1, ChronoUnit.DAYS).toInstant());
            if(searchRequestListDateVaccinationsResults.getFromDate() == null){
                String timestampFormatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(earliest);
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeEarliest = LocalDateTime.from(formatDateTime.parse(timestampFormatted));
                Timestamp timestampToDate = Timestamp.valueOf(localDateTimeEarliest);
                searchRequestListDateVaccinationsResults.setFromDate(timestampToDate);
            }

            List<VaccinationInfoLastDate> vaccinationInfoLastDate = vaccinationInfoRepository.fetchListOfVaccinationPerRegion(searchRequestListDateVaccinationsResults.getFromDate());
            ZonedDateTime zonedDateTime = searchRequestListDateVaccinationsResults.getFromDate().toInstant().atZone(ZoneId.of("UTC"));
            Timestamp yesterday = Timestamp.from(zonedDateTime.minus(1, ChronoUnit.DAYS).toInstant());
            List<VaccinationInfoLastDate> vaccinationInfoYesterday = vaccinationInfoRepository.fetchListOfVaccinationPerRegion(yesterday);

            vaccinationInfoLastDate.forEach(x-> {
                VaccinationInfoLastDateReturn vaccinationInfoLastDateReturn = new VaccinationInfoLastDateReturn();
                vaccinationInfoLastDateReturn.setSumTotalDistinctPersons(x.getSumTotalDistinctPersons());
                vaccinationInfoLastDateReturn.setSumTotalVaccinations(x.getSumTotalVaccinations());
                vaccinationInfoLastDateReturn.setSumTotalDose3(x.getSumTotalDose3());
                vaccinationInfoLastDateReturn.setSumTotalDose2(x.getSumTotalDose2());
                vaccinationInfoLastDateReturn.setReferenceDate(x.getReferenceDate());
                vaccinationInfoLastDateReturn.setAreaGr(x.getAreaGr());

                if(vaccinationInfoYesterday.size() > 0){
                    vaccinationInfoYesterday.forEach(y-> {
                        if(x.getAreaId().equals(y.getAreaId())){
                            vaccinationInfoLastDateReturn.setDayDiffTotalDistinctPersons(x.getSumTotalDistinctPersons() - y.getSumTotalDistinctPersons());
                            vaccinationInfoLastDateReturn.setDayDiffTotalVaccinations(x.getSumTotalVaccinations() - y.getSumTotalVaccinations());
                            vaccinationInfoLastDateReturn.setDayDiffTotalDose3(x.getSumTotalDose3() - y.getSumTotalDose3());
                            vaccinationInfoLastDateReturn.setDayDiffTotalDose2(x.getSumTotalDose2() - y.getSumTotalDose2());

                        }                });
                }
                vaccinationsHistoryDtoReturnList.add(vaccinationInfoLastDateReturn);
            });
//            vaccinationsHistoryDtoReturn.setSumTotalVaccinations(vaccinationInfoLastDate.getSumTotalVaccinations());
//            vaccinationsHistoryDtoReturn.setSumTotalDistinctPersons(vaccinationInfoLastDate.getSumTotalDistinctPersons());
//            vaccinationsHistoryDtoReturn.setSumTotalDose2(vaccinationInfoLastDate.getSumTotalDose2());
//            vaccinationsHistoryDtoReturn.setSumTotalDose3(vaccinationInfoLastDate.getSumTotalDose3());
//            vaccinationsHistoryDtoReturn.setUpdatedAt(earliest);
        }
        return vaccinationsHistoryDtoReturnList;
    }

    @Override
    public List<VaccinationInfoLastDateReturn> getListOfDatesAndTotalVaccinationResults(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults) {
        List<VaccinationInfoLastDateReturn> vaccinationsHistoryDtoReturnList = new ArrayList<>();

//        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
//        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
//        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
//        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
//        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
//        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        Timestamp vaccinationLastUpdate =  platformConfigurationRepository.findByConfigurationVariable("VACCINATION_INFO_LAST_UPDATE").getDateValue();

        if(vaccinationLastUpdate != null){
            Timestamp earliest = vaccinationLastUpdate;
            ZonedDateTime zonedDateTime = earliest.toInstant().atZone(ZoneId.of("UTC"));
            Timestamp yesterday = Timestamp.from(zonedDateTime.minus(1, ChronoUnit.DAYS).toInstant());
            if(searchRequestListDateVaccinationsResults.getFromDate() == null && searchRequestListDateVaccinationsResults.getToDate() != null){
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeFromDate = LocalDateTime.from(formatDateTime.parse("2020-12-28T02:00:00.000Z"));
                Timestamp timestampFromDate = Timestamp.valueOf(localDateTimeFromDate);
                searchRequestListDateVaccinationsResults.setFromDate(timestampFromDate);
            }else if(searchRequestListDateVaccinationsResults.getToDate() == null && searchRequestListDateVaccinationsResults.getFromDate() != null){
                String timestampFormatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(earliest);
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeEarliest = LocalDateTime.from(formatDateTime.parse(timestampFormatted));
                Timestamp timestampToDate = Timestamp.valueOf(localDateTimeEarliest);
                searchRequestListDateVaccinationsResults.setToDate(timestampToDate);
            }else if(searchRequestListDateVaccinationsResults.getToDate() == null && searchRequestListDateVaccinationsResults.getFromDate() == null){
                String timestampFormatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(earliest);
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeEarliest = LocalDateTime.from(formatDateTime.parse(timestampFormatted));
                LocalDateTime localDateTimeFromDate = LocalDateTime.from(formatDateTime.parse("2020-12-28T02:00:00.000Z"));
                Timestamp timestamp = Timestamp.valueOf(localDateTimeEarliest);
                Timestamp setFromDate = Timestamp.valueOf(localDateTimeFromDate);
                searchRequestListDateVaccinationsResults.setToDate(timestamp);
                searchRequestListDateVaccinationsResults.setFromDate(setFromDate);
            }
            List<VaccinationInfoLastDate> vaccinationInfoLastDate = vaccinationInfoRepository.fetchListOfDatesWithTotalResults(searchRequestListDateVaccinationsResults.getFromDate(),searchRequestListDateVaccinationsResults.getToDate());
            vaccinationInfoLastDate.forEach( x-> {
                VaccinationInfoLastDateReturn vaccinationInfoLastDateReturn = new VaccinationInfoLastDateReturn();
                vaccinationInfoLastDateReturn.setSumTotalDistinctPersons(x.getSumTotalDistinctPersons());
                vaccinationInfoLastDateReturn.setSumTotalVaccinations(x.getSumTotalVaccinations());
                vaccinationInfoLastDateReturn.setSumTotalDose3(x.getSumTotalDose3());
                vaccinationInfoLastDateReturn.setSumTotalDose2(x.getSumTotalDose2());
                vaccinationInfoLastDateReturn.setReferenceDate(x.getReferenceDate());
                vaccinationsHistoryDtoReturnList.add(vaccinationInfoLastDateReturn);
            });
//            vaccinationsHistoryDtoReturn.setSumTotalVaccinations(vaccinationInfoLastDate.getSumTotalVaccinations());
//            vaccinationsHistoryDtoReturn.setSumTotalDistinctPersons(vaccinationInfoLastDate.getSumTotalDistinctPersons());
//            vaccinationsHistoryDtoReturn.setSumTotalDose2(vaccinationInfoLastDate.getSumTotalDose2());
//            vaccinationsHistoryDtoReturn.setSumTotalDose3(vaccinationInfoLastDate.getSumTotalDose3());
//            vaccinationsHistoryDtoReturn.setUpdatedAt(earliest);
        }
        return vaccinationsHistoryDtoReturnList;
    }


    @Override
    public VaccinationInfoLastDateReturn getVaccinationDataByDate() {
        VaccinationInfoLastDateReturn vaccinationsHistoryDtoReturn = new VaccinationInfoLastDateReturn();

//        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
//        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
//        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
//        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
//        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
//        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();
        Timestamp vaccinationLastUpdate =  platformConfigurationRepository.findByConfigurationVariable("VACCINATION_INFO_LAST_UPDATE").getDateValue();

        if( vaccinationLastUpdate != null){
            Timestamp earliest = vaccinationLastUpdate;
            ZonedDateTime zonedDateTime = earliest.toInstant().atZone(ZoneId.of("UTC"));
            Timestamp yesterday = Timestamp.from(zonedDateTime.minus(1, ChronoUnit.DAYS).toInstant());
            VaccinationInfoLastDate vaccinationInfoLastDate = vaccinationInfoRepository.fetchLastDateVaccinationResults(earliest);
            VaccinationInfoLastDate vaccinationInfoYesterday = vaccinationInfoRepository.fetchLastDateVaccinationResults(yesterday);
            vaccinationsHistoryDtoReturn.setSumTotalVaccinations(vaccinationInfoLastDate.getSumTotalVaccinations());
            vaccinationsHistoryDtoReturn.setSumTotalDistinctPersons(vaccinationInfoLastDate.getSumTotalDistinctPersons());
            vaccinationsHistoryDtoReturn.setSumTotalDose2(vaccinationInfoLastDate.getSumTotalDose2());
            vaccinationsHistoryDtoReturn.setSumTotalDose3(vaccinationInfoLastDate.getSumTotalDose3());
            vaccinationsHistoryDtoReturn.setUpdatedAt(earliest);
            vaccinationsHistoryDtoReturn.setDayDiffTotalVaccinations(vaccinationInfoLastDate.getSumTotalVaccinations() - vaccinationInfoYesterday.getSumTotalVaccinations());
            vaccinationsHistoryDtoReturn.setDayDiffTotalDose2(vaccinationInfoLastDate.getSumTotalDose2() - vaccinationInfoYesterday.getSumTotalDose2());
            vaccinationsHistoryDtoReturn.setDayDiffTotalDose3(vaccinationInfoLastDate.getSumTotalDose3() - vaccinationInfoYesterday.getSumTotalDose3());
            vaccinationsHistoryDtoReturn.setDayDiffTotalDistinctPersons(vaccinationInfoLastDate.getSumTotalDistinctPersons() - vaccinationInfoYesterday.getSumTotalDistinctPersons());
            vaccinationsHistoryDtoReturn.setYesterdayDate(yesterday);
            Double percentageFullyVaccinated = ((double) vaccinationInfoLastDate.getSumTotalDose2()/11000000)* 100;
            Double percentageSemiVaccinated = ((double) vaccinationInfoLastDate.getSumTotalDistinctPersons()/11000000)* 100;
            Double percentageThirdDoseVaccinated = ((double) vaccinationInfoLastDate.getSumTotalDose3()/11000000)* 100;
            vaccinationsHistoryDtoReturn.setPercentageFullyVaccinated(String.format("%.2f",percentageFullyVaccinated));
            vaccinationsHistoryDtoReturn.setPercentageSemiVaccinated(String.format("%.2f",percentageSemiVaccinated));
            vaccinationsHistoryDtoReturn.setPercentageThirdDoseVaccinated(String.format("%.2f",percentageThirdDoseVaccinated));
        }
        return vaccinationsHistoryDtoReturn;
    }

    public void fetchAndSaveVaccinationHistory() {
        List<VaccinationInfoDTO> vaccinationInfo= new ArrayList<>();
        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("VACCINATION_INFO_LAST_UPDATE");
        if(platformConfiguration!= null){
            if(platformConfiguration.getDateValue() != null){
                vaccinationInfo = dataGovController.getAllVaccinationInfo();
                List<VaccinationInfoDTO> vaccinationInfoFilteredListByDate= new ArrayList<>();
                vaccinationInfoFilteredListByDate =
                        vaccinationInfo
                                .stream()
                                .filter( event -> event.getReferencedate().compareTo(platformConfiguration.getDateValue()) > 0 )
                                .collect( Collectors.toList() )
                ;
                saveVaccinationHistory(vaccinationInfo, platformConfiguration, vaccinationInfoFilteredListByDate);
            }else{
                vaccinationInfo = dataGovController.getAllVaccinationInfo();
                saveVaccinationHistory(vaccinationInfo, platformConfiguration, vaccinationInfo);
            }
        }
    }

    private void saveVaccinationHistory(List<VaccinationInfoDTO> vaccinationInfo, PlatformConfiguration platformConfiguration, List<VaccinationInfoDTO> vaccinationInfoFilteredListByDate) {
        Collections.sort(vaccinationInfoFilteredListByDate, new SortByDate());
        if (vaccinationInfoFilteredListByDate.size() != 0) {
            vaccinationInfoFilteredListByDate.forEach(x -> {
                    if(x.getArea_en().contains("Athens") || x.getArea_en().contains("Attica")){
                        VaccinationInfo vaccinationInfoInDatabase = vaccinationInfoRepository.findByReferencedateAndAreas(x.getReferencedate(),areasRepository.findByAreaGr("ΑΤΤΙΚΗΣ"));
                        if(vaccinationInfoInDatabase != null){
                            vaccinationInfoInDatabase.setTotalvaccinations(vaccinationInfoInDatabase.getTotalvaccinations() + x.getTotalvaccinations());
                            vaccinationInfoInDatabase.setDailydose1(vaccinationInfoInDatabase.getDailydose1() + x.getDailydose1());
                            vaccinationInfoInDatabase.setDailydose2(vaccinationInfoInDatabase.getDailydose2() + x.getDailydose2());
                            vaccinationInfoInDatabase.setDailydose3(vaccinationInfoInDatabase.getDailydose3() + x.getDailydose3());
                            vaccinationInfoInDatabase.setDaydiff(vaccinationInfoInDatabase.getDaydiff() + x.getDaydiff());
                            vaccinationInfoInDatabase.setDaytotal(vaccinationInfoInDatabase.getDaytotal() + x.getDaytotal());
                            vaccinationInfoInDatabase.setTotaldistinctpersons(vaccinationInfoInDatabase.getTotaldistinctpersons() + x.getTotaldistinctpersons());
                            vaccinationInfoInDatabase.setTotaldose1(vaccinationInfoInDatabase.getTotaldose1() + x.getTotaldose1());
                            vaccinationInfoInDatabase.setTotaldose2(vaccinationInfoInDatabase.getTotaldose2() + x.getTotaldose2());
                            vaccinationInfoInDatabase.setTotaldose3(vaccinationInfoInDatabase.getTotaldose3() + x.getTotaldose3());
                            vaccinationInfoRepository.save(vaccinationInfoInDatabase);
                        }else{
                            VaccinationInfo vaccinationInfoToBeAdded = new VaccinationInfo();
                            vaccinationInfoToBeAdded.setTotalvaccinations(x.getTotalvaccinations());
                            vaccinationInfoToBeAdded.setDailydose1(x.getDailydose1());
                            vaccinationInfoToBeAdded.setDailydose2(x.getDailydose2());
                            vaccinationInfoToBeAdded.setDailydose3(x.getDailydose3());
                            vaccinationInfoToBeAdded.setDaydiff(x.getDaydiff());
                            vaccinationInfoToBeAdded.setDaytotal(x.getDaytotal());
                            vaccinationInfoToBeAdded.setTotaldistinctpersons(x.getTotaldistinctpersons());
                            vaccinationInfoToBeAdded.setTotaldose1(x.getTotaldose1());
                            vaccinationInfoToBeAdded.setTotaldose2(x.getTotaldose2());
                            vaccinationInfoToBeAdded.setTotaldose3(x.getTotaldose3());
                            vaccinationInfoToBeAdded.setReferencedate(x.getReferencedate());
                            vaccinationInfoToBeAdded.setAreas(areasRepository.findByAreaGr("ΑΤΤΙΚΗΣ"));
                            vaccinationInfoRepository.save(vaccinationInfoToBeAdded);
                        }
                    }else{
                        VaccinationInfo vaccinationInfoObject = new VaccinationInfo();
                        vaccinationInfoObject.setDailydose1(x.getDailydose1());
                        vaccinationInfoObject.setDailydose2(x.getDailydose2());
                        vaccinationInfoObject.setDailydose3(x.getDailydose3());
                        vaccinationInfoObject.setDaydiff(x.getDaydiff());
                        vaccinationInfoObject.setDaytotal(x.getDaytotal());
                        vaccinationInfoObject.setReferencedate(x.getReferencedate());
                        vaccinationInfoObject.setTotaldistinctpersons(x.getTotaldistinctpersons());
                        vaccinationInfoObject.setTotaldose1(x.getTotaldose1());
                        vaccinationInfoObject.setTotaldose2(x.getTotaldose2());
                        vaccinationInfoObject.setTotaldose3(x.getTotaldose3());
                        vaccinationInfoObject.setTotalvaccinations(x.getTotalvaccinations());
                        if (areasRepository.findByAreaGr(x.getArea_gr()) != null) {
                            vaccinationInfoObject.setAreas(areasRepository.findByAreaGr(x.getArea_gr()));
                        }else{
                            Areas areas = new Areas();
                            areas.setAreaEn(x.getArea_en());
                            areas.setAreaGr(x.getArea_gr());
                            vaccinationInfoObject.setAreas(areasRepository.save(areas));
                        }
                        vaccinationInfoRepository.save(vaccinationInfoObject);
                    }                    ;
            });
            platformConfiguration.setDateValue(vaccinationInfoFilteredListByDate.get(vaccinationInfoFilteredListByDate.size() - 1).getReferencedate());
            platformConfigurationRepository.save(platformConfiguration);
        }
    }


    static class SortByDate implements Comparator<VaccinationInfoDTO> {
        @Override
        public int compare(VaccinationInfoDTO a, VaccinationInfoDTO b) {
            return a.getReferencedate().compareTo(b.getReferencedate());
        }
    }
}
