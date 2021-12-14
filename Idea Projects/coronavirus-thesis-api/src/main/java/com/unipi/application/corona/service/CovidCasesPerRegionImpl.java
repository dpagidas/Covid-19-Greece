package com.unipi.application.corona.service;


import com.unipi.application.corona.apis.RegionCasesHistory.controller.RegionCasesApiController;
import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionCasesHistoryDto;
import com.unipi.application.corona.dto.CasesByRegionDtoReturn;
import com.unipi.application.corona.dto.RegionCasesHistoryDtoReturn;
import com.unipi.application.corona.dto.VaccinationInfoLastDateReturn;
import com.unipi.application.corona.models.*;
import com.unipi.application.corona.models.projection.CasesListByRegionAndByDate;
import com.unipi.application.corona.models.projection.VaccinationInfoLastDate;
import com.unipi.application.corona.models.repository.AreasRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import com.unipi.application.corona.models.repository.RegionCasesHistoryRepository;
import com.unipi.application.corona.models.repository.RegionRepository;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CovidCasesPerRegionImpl implements CovidCasesPerRegionService{

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private RegionCasesApiController regionCasesApiController;

    @Autowired
    private RegionCasesHistoryRepository regionCasesHistoryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private AreasRepository areasRepository;

    @Autowired
    private AreasServiceImpl areasService;

    @Override
    public List<RegionCasesHistoryDtoReturn> getCovidCasesPerRegion() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<RegionCasesHistoryDtoReturn> regionCasesHistoryDtoReturnList = new ArrayList<>();
        regionCasesHistoryRepository.findAll().forEach(x -> {
            regionCasesHistoryDtoReturnList.add(modelMapper.map(x, RegionCasesHistoryDtoReturn.class));
        });
        return regionCasesHistoryDtoReturnList;
    }


    @Override
    public List<CasesByRegionDtoReturn> getListOfCasesByRegion(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults) {
        List<CasesByRegionDtoReturn> casesByRegionDtoReturns = new ArrayList<>();

        Timestamp regionCaseHistoryLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE").getDateValue();
        Timestamp genderAgeLastUpdatedDate = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE").getDateValue();
        Timestamp intensiveCareLastUpdateDate = platformConfigurationRepository.findByConfigurationVariable("INTENSIVE_CARE_LAST_UPDATE").getDateValue();
        Timestamp confirmedCasesLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_CASES_LAST_UPDATE").getDateValue();
        Timestamp totalTestsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("TOTAL_TESTS_LAST_UPDATE").getDateValue();
        Timestamp confirmedDeathsLastUpdate = platformConfigurationRepository.findByConfigurationVariable("CONFIRMED_DEATHS_LAST_UPDATE").getDateValue();

        if(regionCaseHistoryLastUpdatedDate != null && genderAgeLastUpdatedDate != null && intensiveCareLastUpdateDate != null && confirmedCasesLastUpdate != null && totalTestsLastUpdate != null && confirmedDeathsLastUpdate != null){
            Timestamp earliest = Collections.min(Arrays.asList(regionCaseHistoryLastUpdatedDate, genderAgeLastUpdatedDate, intensiveCareLastUpdateDate, confirmedCasesLastUpdate, totalTestsLastUpdate, confirmedDeathsLastUpdate));
//            ZonedDateTime zonedDateTime = earliest.toInstant().atZone(ZoneId.of("UTC"));
//            Timestamp yesterday = Timestamp.from(zonedDateTime.minus(1, ChronoUnit.DAYS).toInstant());
            if(searchRequestListDateVaccinationsResults.getFromDate() == null){
                String timestampFormatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(earliest);
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime localDateTimeEarliest = LocalDateTime.from(formatDateTime.parse(timestampFormatted));
                Timestamp timestampToDate = Timestamp.valueOf(localDateTimeEarliest);
                searchRequestListDateVaccinationsResults.setFromDate(timestampToDate);
            }

            List<CasesListByRegionAndByDate> casesListByRegionAndByDatesLastDate = regionCasesHistoryRepository.getListCasesByRegionAndByDate(searchRequestListDateVaccinationsResults.getFromDate());
            ZonedDateTime zonedDateTime = searchRequestListDateVaccinationsResults.getFromDate().toInstant().atZone(ZoneId.of("UTC"));
            Timestamp yesterday = Timestamp.from(zonedDateTime.minus(1, ChronoUnit.DAYS).toInstant());
            List<CasesListByRegionAndByDate> casesListByRegionAndByDatesYesterday = regionCasesHistoryRepository.getListCasesByRegionAndByDate(yesterday);

            casesListByRegionAndByDatesLastDate.forEach(x-> {
                CasesByRegionDtoReturn casesByRegionDtoReturn = new CasesByRegionDtoReturn();
                casesByRegionDtoReturn.setCases(x.getCases());
                casesByRegionDtoReturn.setDate(x.getDate());
                casesByRegionDtoReturn.setAreaGr(x.getAreaGr());

                if(casesListByRegionAndByDatesYesterday.size() > 0){
                    casesListByRegionAndByDatesYesterday.forEach(y-> {
                        if(x.getAreaId().equals(y.getAreaId())){
                            casesByRegionDtoReturn.setDiffCases(x.getCases() - y.getCases());
                        }});
                }
                casesByRegionDtoReturns.add(casesByRegionDtoReturn);
            });
//            vaccinationsHistoryDtoReturn.setSumTotalVaccinations(vaccinationInfoLastDate.getSumTotalVaccinations());
//            vaccinationsHistoryDtoReturn.setSumTotalDistinctPersons(vaccinationInfoLastDate.getSumTotalDistinctPersons());
//            vaccinationsHistoryDtoReturn.setSumTotalDose2(vaccinationInfoLastDate.getSumTotalDose2());
//            vaccinationsHistoryDtoReturn.setSumTotalDose3(vaccinationInfoLastDate.getSumTotalDose3());
//            vaccinationsHistoryDtoReturn.setUpdatedAt(earliest);
        }
        return casesByRegionDtoReturns;
    }

    public void fetchAndSaveCovidCaseRegionHistory() {
        List<RegionCasesHistoryDto> regionCasesHistoryList= new ArrayList<>();

        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("REGION_CASE_HISTORY_NEWS_LAST_UPDATE");
        if(platformConfiguration!= null){
            if(platformConfiguration.getDateValue() != null){
                regionCasesHistoryList = regionCasesApiController.getRegionCasesHistory();
                List<RegionCasesHistoryDto> regionCasesHistoryFilteredByDate= new ArrayList<>();
                regionCasesHistoryFilteredByDate =
                        regionCasesHistoryList
                                .stream()
                                .filter( event -> event.getDate().compareTo(platformConfiguration.getDateValue()) > 0 )
                                .collect( Collectors.toList() )
                ;
                saveRegionCaseHistory(regionCasesHistoryList, platformConfiguration, regionCasesHistoryFilteredByDate);
            }else{
                regionCasesHistoryList = regionCasesApiController.getRegionCasesHistory();
                saveRegionCaseHistory(regionCasesHistoryList, platformConfiguration, regionCasesHistoryList);
            }
        }
    }

    private void saveRegionCaseHistory(List<RegionCasesHistoryDto> regionCasesHistoryList, PlatformConfiguration platformConfiguration, List<RegionCasesHistoryDto> regionCasesHistoryFilteredByDate) {
        Collections.sort(regionCasesHistoryFilteredByDate, new SortByDate());
        if (regionCasesHistoryFilteredByDate.size() != 0) {
            regionCasesHistoryList.forEach(x -> {
                List<Region> regionList = new ArrayList<>();
                RegionCasesHistory regionCasesHistory = new RegionCasesHistory();
                regionCasesHistory.setDate(x.getDate());
                x.getRegions().forEach(y -> {
                    Region region = new Region();
                    region.setCases(y.getCases());
                    region.setGeoDepartmentEn(y.getGeo_department_en());
                    region.setGeoDepartmentGr(y.getGeo_department_gr());
                    region.setLastUpdatedAt(y.getLast_updated_at());
                    if (areasRepository.findByAreaGr(y.getArea_gr()) != null) {
                        region.setAreas(areasRepository.findByAreaGr(y.getArea_gr()));
                    } else {
                        areasService.getAreas();
                        region.setAreas(areasRepository.findByAreaGr(y.getArea_gr()));

                    }
                    region.setRegionCasesHistory(regionCasesHistory);
                    regionList.add(region);
                });
                regionCasesHistory.setRegions(regionList);
                regionCasesHistoryRepository.save(regionCasesHistory);
            });
            platformConfiguration.setDateValue(regionCasesHistoryFilteredByDate.get(regionCasesHistoryFilteredByDate.size() - 1).getDate());
            platformConfigurationRepository.save(platformConfiguration);
        }
    }


    static class SortByDate implements Comparator<RegionCasesHistoryDto> {
        @Override
        public int compare(RegionCasesHistoryDto a, RegionCasesHistoryDto b) {
            return a.getDate().compareTo(b.getDate());
        }
    }
}
