package com.unipi.application.corona.apis.RegionCasesHistory.controller;

import com.unipi.application.corona.apis.RegionCasesHistory.service.RegionCasesApiService;
import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionCasesHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionCasesApiController {
    @Autowired
    private RegionCasesApiService regionCasesApiService;

    public List<RegionCasesHistoryDto> getRegionCasesHistory() {
        return regionCasesApiService.getRegionCasesHistory();
    }
}
