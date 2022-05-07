package com.unipi.application.corona.apis.RegionCasesHistory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipi.application.corona.config.WebClientConfiguration;
import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionCasesHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegionCasesApiService {

    @Autowired
    WebClientConfiguration webClient;

    public List<RegionCasesHistoryDto> getRegionCasesHistory() {

        List<RegionCasesHistoryDto> regionCasesHistoryList = new ArrayList<>();
        try {

            Mono<String> stringMono = webClient.buildVaccinationApiRequest()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/regions-history")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode data = objectMapper.readTree(stringMono.block());
                JsonNode documents = objectMapper.readTree(data.findValue("regions-history").toString());

                ObjectMapper modelMapper = new ObjectMapper();
                regionCasesHistoryList = modelMapper.readValue(documents.toString(), new TypeReference<List<RegionCasesHistoryDto>>() {
                });
                return regionCasesHistoryList;

            } catch (IOException e) {
                e.printStackTrace();
//                    utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to map JSON with Document", e);
            }

        }catch(SSLException e){
//                utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to request Token from DMS", e);
            e.printStackTrace();
        }
        return regionCasesHistoryList;
    }
}
