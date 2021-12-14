package com.unipi.application.corona.apis.Covid19NewsApi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unipi.application.corona.config.WebClientConfiguration;
import com.unipi.application.corona.models.Covid19News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsApiService {

    @Autowired
    WebClientConfiguration webClient;

    public List<Covid19News> getLatestCovid19News() {

        List<Covid19News> vaccinationInfoList = new ArrayList<>();
        try {

            Mono<String> stringMono = webClient.buildCovid19ApiRequest()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v2/top-headlines")
                            .queryParam("country","gr")
                            .queryParam("apiKey", "d97de4f165254b0ca6b881756b5701b1")
                            .queryParam("q", "κορ")
                            .queryParam("category", "health")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode data = objectMapper.readTree(stringMono.block());
                JsonNode documents = objectMapper.readTree(data.findValue("articles").toString());

                for (JsonNode personNode : documents) {
                    if (personNode instanceof ObjectNode) {
                        if (personNode.has("source")) {
                            ObjectNode object = (ObjectNode) personNode;
                            object.remove("source");
                        }
                    }
                }

                ObjectMapper modelMapper = new ObjectMapper();
                vaccinationInfoList = modelMapper.readValue(documents.toString(), new TypeReference<List<Covid19News>>() {
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
