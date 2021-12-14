package com.unipi.application.corona.service;

import com.unipi.application.corona.dto.ConfirmedDeathsDtoReturn;
import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.ConfirmedDeaths;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfirmedDeathsService {

    List<ConfirmedDeaths> getConfirmedDeaths();

    ConfirmedDeathsDtoReturn getConfirmedDeathsLastDate();
}
