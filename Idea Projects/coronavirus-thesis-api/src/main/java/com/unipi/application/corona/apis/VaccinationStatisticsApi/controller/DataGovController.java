package com.unipi.application.corona.apis.VaccinationStatisticsApi.controller;

import com.unipi.application.corona.apis.VaccinationStatisticsApi.dto.VaccinationInfoDTO;
import com.unipi.application.corona.apis.VaccinationStatisticsApi.service.DataGovService;
import com.unipi.application.corona.models.VaccinationInfo;
import com.unipi.application.corona.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataGovController {

    @Autowired
    private DataGovService dataGovService;

    @Autowired
    Utils utils;

    public List<VaccinationInfoDTO> getAllVaccinationInfo() {
        return dataGovService.getAllVaccination();

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
