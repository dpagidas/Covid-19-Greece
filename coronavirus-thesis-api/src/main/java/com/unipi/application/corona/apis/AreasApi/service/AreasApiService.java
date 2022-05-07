package com.unipi.application.corona.apis.AreasApi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unipi.application.corona.apis.AreasApi.dto.AreasDto;
import com.unipi.application.corona.config.WebClientConfiguration;
import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.Covid19News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.awt.geom.Area;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AreasApiService {

    @Autowired
    WebClientConfiguration webClient;

    public List<AreasDto> getAreas() {

        List<AreasDto> areasList = new ArrayList<>();
        try {

            Mono<String> stringMono = webClient.buildVaccinationApiRequest()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/regions")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode data = objectMapper.readTree(stringMono.block());
                JsonNode documents = objectMapper.readTree(data.findValue("regions").toString());

                for (JsonNode personNode : documents) {
                    if (personNode instanceof ObjectNode) {
                        if (personNode.has("source")) {
                            ObjectNode object = (ObjectNode) personNode;
                            object.remove("source");
                        }
                    }
                }

                ObjectMapper modelMapper = new ObjectMapper();
                areasList = modelMapper.readValue(documents.toString(), new TypeReference<List<AreasDto>>() {
                });
                return areasList;

            } catch (IOException e) {
                e.printStackTrace();
//                    utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to map JSON with Document", e);
            }

        }catch(SSLException e){
//                utils.saveSystemException(null, null, JobsEnum.DMS_JOB.toString(), null, "Failed to request Token from DMS", e);
            e.printStackTrace();
        }
        return areasList;
    }
}
