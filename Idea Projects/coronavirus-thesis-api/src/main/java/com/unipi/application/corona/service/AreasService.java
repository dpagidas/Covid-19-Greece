package com.unipi.application.corona.service;

import com.unipi.application.corona.dto.VaccinationsHistoryDtoReturn;
import com.unipi.application.corona.models.Areas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AreasService {

    List<Areas> getAreas();
}
