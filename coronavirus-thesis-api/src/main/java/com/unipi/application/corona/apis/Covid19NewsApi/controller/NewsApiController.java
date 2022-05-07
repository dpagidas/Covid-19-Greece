package com.unipi.application.corona.apis.Covid19NewsApi.controller;

import com.unipi.application.corona.apis.Covid19NewsApi.service.NewsApiService;
import com.unipi.application.corona.models.Covid19News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NewsApiController {

    @Autowired
    private NewsApiService newsApiService;

    public List<Covid19News> getVaccinationInfoByPeriod() {
        return newsApiService.getLatestCovid19News();

//        ModelMapper modelMapper = utils.initModelMapperStrict();
//        List<VaccinationInfoDTO> records = new ArrayList<>();
//        List<InvoiceMasterTemp>  invoices = invoiceMasterTempRepository.findByIdBatchId(batchId);
//        invoices.forEach(invoice -> {
//            VaccinationInfoDTO record = modelMapper.map(invoice, VaccinationInfoDTO.class);
//            record.setDocumentSubclassificationId(docSubclassification.codeToId(record.getDocumentSubclassificationCode()));
//            record.setAbbyyConfidenceLvl(100);
//            record.setReceivedFileId(item.getId());
//            record.setIpowerClientId(item.getDmsClientCode());
//            record.setDmsFileCode(item.getDmsFileCode());
//            int numberOfDetailLines = 0;
//            if (record.getInvoiceDetailsData() != null) {
//                numberOfDetailLines = record.getInvoiceDetailsData().size();
//            }
//            record.setNumberOfDetailLines(numberOfDetailLines);
//            records.add(record);
//        });
//
//        return null;
    }
}
