package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.Covid19NewsApi.controller.NewsApiController;
import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionCasesHistoryDto;
import com.unipi.application.corona.dto.RegionCasesHistoryDtoReturn;
import com.unipi.application.corona.dto.SearchResponseModel;
import com.unipi.application.corona.models.Covid19News;
import com.unipi.application.corona.models.PlatformConfiguration;
import com.unipi.application.corona.models.Region;
import com.unipi.application.corona.models.RegionCasesHistory;
import com.unipi.application.corona.models.repository.Covid19NewsRepository;
import com.unipi.application.corona.models.repository.PlatformConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Covid19NewsInfoServiceImpl implements Covid19NewsInfoService {

    @Autowired
    private NewsApiController newsApiController;

    @Autowired
    private Covid19NewsRepository covid19NewsRepository;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

//    public List<Covid19News> getLatestCovid19News() {
//        List<Covid19News> covid19News = new ArrayList<>();
//        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("COVID19_NEWS_LAST_UPDATE");
//        if (platformConfiguration != null) {
//            if (platformConfiguration.getDateValue() != null) {
//                Date date = new Date(platformConfiguration.getDateValue().getTime());
//                if (new Date().after(date)) {
//                    covid19News = newsApiController.getVaccinationInfoByPeriod();
//                    Collections.sort(covid19News, new Covid19NewsInfoServiceImpl.SortByDate());
//                    covid19News.removeIf(covid19News1 -> covid19News1.getPublishedAt().compareTo(platformConfiguration.getDateValue()) <= 0 );
//                    if(covid19News.size() !=0){
//                        platformConfiguration.setDateValue(covid19News.get(covid19News.size() - 1).getPublishedAt());
//                    }
//                    covid19NewsRepository.saveAll(covid19News);
//                }
//            } else {
//                covid19News = newsApiController.getVaccinationInfoByPeriod();
//                Collections.sort(covid19News, new Covid19NewsInfoServiceImpl.SortByDate());
//                if(covid19News.size() !=0){
//                    platformConfiguration.setDateValue(covid19News.get(covid19News.size() - 1).getPublishedAt());
//                }
//                covid19NewsRepository.saveAll(covid19News);
//            }
//        }
//        return covid19NewsRepository.findAll();
//    }



    @Override
    public List<Covid19News> getCovid19News() {
        return covid19NewsRepository.findAll();
    }


    @Override
    public SearchResponseModel getPagedCovid19News(int page, int offset) {
        SearchResponseModel searchResponseModel = new SearchResponseModel();

        Pageable pageable = PageRequest.of(page, offset, Sort.by("publishedAt").descending());
        Page<Covid19News> covid19NewsPage = covid19NewsRepository.findAll(pageable);
        searchResponseModel.setData(covid19NewsPage.getContent());
        searchResponseModel.setTotalElements(covid19NewsPage.getTotalElements());
        searchResponseModel.setTotalPages(covid19NewsPage.getTotalPages());

        return searchResponseModel;
    }

    public void fetchAndSaveCovid19News() {
        List<Covid19News> covid19News = new ArrayList<>();
        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("COVID19_NEWS_LAST_UPDATE");
        if (platformConfiguration != null) {
            if (platformConfiguration.getDateValue() != null) {
                Date date = new Date(platformConfiguration.getDateValue().getTime());
                if (new Date().after(date)) {
                    covid19News = newsApiController.getVaccinationInfoByPeriod();
                    covid19News.removeIf(covid19News1 -> covid19News1.getPublishedAt().compareTo(platformConfiguration.getDateValue()) <= 0 );
                    if(covid19News.size() !=0){
                        platformConfiguration.setDateValue(covid19News.get(covid19News.size() - 1).getPublishedAt());
                    }
                    saveCovid19News(covid19News, platformConfiguration);
                }
            } else {
                covid19News = newsApiController.getVaccinationInfoByPeriod();
                saveCovid19News(covid19News, platformConfiguration);
            }
        }
    }

    private void saveCovid19News(List<Covid19News> covid19News, PlatformConfiguration platformConfiguration) {
        Collections.sort(covid19News, new SortByDate());
        if(covid19News.size() !=0){
            platformConfiguration.setDateValue(covid19News.get(covid19News.size() - 1).getPublishedAt());
            platformConfigurationRepository.save(platformConfiguration);
        }
        covid19NewsRepository.saveAll(covid19News);
    }

    static class SortByDate implements Comparator<Covid19News> {
        @Override
        public int compare(Covid19News a, Covid19News b) {
            return a.getPublishedAt().compareTo(b.getPublishedAt());
        }
    }
}
