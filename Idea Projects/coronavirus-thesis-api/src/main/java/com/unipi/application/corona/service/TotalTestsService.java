package com.unipi.application.corona.service;

import com.unipi.application.corona.dto.TotalTestByDateDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.TotalTests;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TotalTestsService {

    List<TotalTests> getTotalTests();

    TotalTestByDateDtoReturn getTotalTestByLastDate();


}
