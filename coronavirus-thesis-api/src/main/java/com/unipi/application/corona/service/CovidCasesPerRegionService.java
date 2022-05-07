package com.unipi.application.corona.service;

import com.unipi.application.corona.dto.CasesByRegionDtoReturn;
import com.unipi.application.corona.dto.RegionCasesHistoryDtoReturn;
import com.unipi.application.corona.models.Covid19News;
import com.unipi.application.corona.models.Region;
import com.unipi.application.corona.models.RegionCasesHistory;
import com.unipi.application.corona.models.VaccinationInfo;
import com.unipi.application.corona.models.projection.CasesListByDate;
import com.unipi.application.corona.models.projection.CasesListByRegionAndByDate;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface CovidCasesPerRegionService {

    public List<RegionCasesHistoryDtoReturn> getCovidCasesPerRegion();

    public List<CasesByRegionDtoReturn> getListOfCasesByRegion(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults);



}
