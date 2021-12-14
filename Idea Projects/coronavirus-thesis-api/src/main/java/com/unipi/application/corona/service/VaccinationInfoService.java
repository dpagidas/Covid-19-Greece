package com.unipi.application.corona.service;

import com.unipi.application.corona.dto.VaccinationInfoLastDateReturn;
import com.unipi.application.corona.dto.VaccinationsHistoryDtoReturn;
import com.unipi.application.corona.models.VaccinationInfo;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface VaccinationInfoService {

    List<VaccinationsHistoryDtoReturn> getVaccinationHistory();

    VaccinationInfoLastDateReturn getVaccinationDataByDate();

    List<VaccinationInfoLastDateReturn> getListOfDatesAndTotalVaccinationResults(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults);

    List<VaccinationInfoLastDateReturn> getListOfVaccinationPerRegion(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults);
}
