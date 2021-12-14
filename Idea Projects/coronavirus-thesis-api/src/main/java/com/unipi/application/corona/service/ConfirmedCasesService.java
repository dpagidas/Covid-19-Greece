package com.unipi.application.corona.service;

import com.unipi.application.corona.dto.CasesListByDateDtoReturn;
import com.unipi.application.corona.dto.ConfirmedCasesDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.models.request.SearchRequestListDateVaccinationsResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfirmedCasesService {

    List<ConfirmedCases> getConfirmedCases();

    ConfirmedCasesDtoReturn getLastDateConfirmedCases();

    List<CasesListByDateDtoReturn> getListPerDayCasesData(SearchRequestListDateVaccinationsResults searchRequestListDateVaccinationsResults);
}
