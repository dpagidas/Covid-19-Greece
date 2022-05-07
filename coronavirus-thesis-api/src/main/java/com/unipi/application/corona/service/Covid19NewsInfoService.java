package com.unipi.application.corona.service;

import com.unipi.application.corona.dto.SearchResponseModel;
import com.unipi.application.corona.models.Covid19News;
import com.unipi.application.corona.models.VaccinationInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Covid19NewsInfoService {

    List<Covid19News> getCovid19News();

    SearchResponseModel getPagedCovid19News(int page, int offset);
}
