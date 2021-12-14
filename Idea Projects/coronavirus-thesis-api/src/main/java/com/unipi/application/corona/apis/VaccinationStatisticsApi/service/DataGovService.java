package com.unipi.application.corona.apis.VaccinationStatisticsApi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipi.application.corona.apis.VaccinationStatisticsApi.dto.VaccinationInfoDTO;
import com.unipi.application.corona.config.WebClientConfiguration;
import com.unipi.application.corona.models.VaccinationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Mono;

@Component
public class DataGovService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WebClientConfiguration webClient;

//    public List<VaccinationInfo> getVaccinationByPeriod(String dateFrom, String dateTo) {
//
//            List<VaccinationInfo> vaccinationInfoList = new ArrayList<>();
//            try {
//
//                Mono<String> stringMono = webClient.buildVaccinationApiRequest()
//                        .get()
//                        .uri(uriBuilder -> uriBuilder
//                            .path("/api/v1/query/mdg_emvolio")
//                            .queryParam("date_from",dateFrom)
//                            .queryParam("date_to", dateTo)
//                            .build())
//                        .header("Authorization", "Token 92faf8c373f94b6f94a756928e85abafe2836ff0")
//                        .retrieve()
//                        .bodyToMono(String.class);
//
//                ObjectMapper objectMapper = new ObjectMapper();
//                try {
//                    JsonNode data = objectMapper.readTree(stringMono.block());
//                    JsonNode documents = objectMapper.readTree(data.toString());
//
//                    ObjectMapper modelMapper = new ObjectMapper();
//                    vaccinationInfoList = modelMapper.readValue(documents.toString(), new TypeReference<List<VaccinationInfo>>() {
//                    });
//                    return vaccinationInfoList;
//
//                } catch (IOException e) {
//                    e.printStackTrace();
////                    utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to map JSON with Document", e);
//                }
//
//            }catch(SSLException e){
////                utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to request Token from DMS", e);
//                e.printStackTrace();
//            }
//            return vaccinationInfoList;
//    }
//
//    public List<VaccinationInfo> getAllVaccinationInfo() {
//
//        List<VaccinationInfo> vaccinationInfoList = new ArrayList<>();
//        try {
//
//            Mono<String> stringMono = webClient.buildVaccinationApiRequest()
//                    .get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/api/v1/query/mdg_emvolio")
//                            .build())
//                    .header("Authorization", "Token 92faf8c373f94b6f94a756928e85abafe2836ff0")
//                    .retrieve()
//                    .bodyToMono(String.class);
//
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                JsonNode data = objectMapper.readTree(stringMono.block());
//                JsonNode documents = objectMapper.readTree(data.toString());
//
//                ObjectMapper modelMapper = new ObjectMapper();
//                vaccinationInfoList = modelMapper.readValue(documents.toString(), new TypeReference<List<VaccinationInfo>>() {
//                });
//                return vaccinationInfoList;
//
//            } catch (IOException e) {
//                e.printStackTrace();
////                    utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to map JSON with Document", e);
//            }
//
//        }catch(SSLException e){
////                utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to request Token from DMS", e);
//            e.printStackTrace();
//        }
//        return vaccinationInfoList;
//    }



    public List<VaccinationInfoDTO> getAllVaccination() {

        List<VaccinationInfoDTO> vaccinationInfoList = new ArrayList<>();
        try {

            Mono<String> stringMono = webClient.buildVaccinationApiRequest()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/vaccinations-per-region-history")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode data = objectMapper.readTree(stringMono.block());
                JsonNode documents = objectMapper.readTree(data.findValue("vaccinations-history").toString());

                ObjectMapper modelMapper = new ObjectMapper();
                vaccinationInfoList = modelMapper.readValue(documents.toString(), new TypeReference<List<VaccinationInfoDTO>>() {
                });
                return vaccinationInfoList;

            } catch (IOException e) {
                e.printStackTrace();
//                    utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to map JSON with Document", e);
            }

        }catch(SSLException e){
//                utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to request Token from DMS", e);
            e.printStackTrace();
        }
        return vaccinationInfoList;
    }

}
