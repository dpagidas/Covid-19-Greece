package com.unipi.application.corona.service;

import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.IntensiveCare;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntensiveCareService {

    List<IntensiveCare> getIntensiveCareCases();

    IntensiveCare getIntensiveCareCasesLastDate();
}
