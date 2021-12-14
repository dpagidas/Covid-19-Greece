package com.unipi.application.corona.service;

import com.unipi.application.corona.apis.GenderAgeDistributionApi.controller.GenderAgeDistributionApiController;
import com.unipi.application.corona.apis.GenderAgeDistributionApi.dto.Root;
import com.unipi.application.corona.apis.RegionCasesHistory.dto.RegionCasesHistoryDto;
import com.unipi.application.corona.dto.RegionCasesHistoryDtoReturn;
import com.unipi.application.corona.models.*;
import com.unipi.application.corona.models.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderAgeDistributionImpl implements GenderAgeDistributionService{

    @Autowired
    private GenderAgeDistributionApiController genderAgeDistributionApiController;

    @Autowired
    private PlatformConfigurationRepository platformConfigurationRepository;

    @Autowired
    private GenderAgeRepository genderAgeRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CriticalRepository criticalRepository;

    @Autowired
    private DeathRepository deathRepository;

    @Autowired
    private FemaleRepository femaleRepository;

    @Autowired
    private MaleRepository maleRepository;


//    public GenderAgeDistribution getGenderAgeDistributionHistory() {
//        Root genderAgeDistributionHistory = new Root();
//        genderAgeDistributionHistory = genderAgeDistributionApiController.getAgeDistributionHistory();
//        GenderAgeDistribution ageDistributionInDatabase = genderAgeRepository.findByUpdateAt(genderAgeDistributionHistory.getUpdated());
//            if(ageDistributionInDatabase != null){
//                ageDistributionInDatabase.getFemales().getCases().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017());
//                ageDistributionInDatabase.getFemales().getCases().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839());
//                ageDistributionInDatabase.getFemales().getCases().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65());
//                ageDistributionInDatabase.getFemales().getCases().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064());
//                ageDistributionInDatabase.getFemales().getCritical().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_017());
//                ageDistributionInDatabase.getFemales().getCritical().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_1839());
//                ageDistributionInDatabase.getFemales().getCritical().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_65());
//                ageDistributionInDatabase.getFemales().getCritical().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_4064());
//                ageDistributionInDatabase.getFemales().getDeaths().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_017());
//                ageDistributionInDatabase.getFemales().getDeaths().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_1839());
//                ageDistributionInDatabase.getFemales().getDeaths().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_65());
//                ageDistributionInDatabase.getFemales().getDeaths().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_4064());
//                ageDistributionInDatabase.getMales().getCases().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017());
//                ageDistributionInDatabase.getMales().getCases().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839());
//                ageDistributionInDatabase.getMales().getCases().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65());
//                ageDistributionInDatabase.getMales().getCases().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064());
//                ageDistributionInDatabase.getMales().getCritical().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_017());
//                ageDistributionInDatabase.getMales().getCritical().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_1839());
//                ageDistributionInDatabase.getMales().getCritical().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_65());
//                ageDistributionInDatabase.getMales().getCritical().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_4064());
//                ageDistributionInDatabase.getMales().getDeaths().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_017());
//                ageDistributionInDatabase.getMales().getDeaths().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_1839());
//                ageDistributionInDatabase.getMales().getDeaths().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_65());
//                ageDistributionInDatabase.getMales().getDeaths().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_4064());
//                ageDistributionInDatabase.setUpdateAt(genderAgeDistributionHistory.getUpdated());
//
//                int sumOfMaleCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839() +
//                        genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064();
//                int sumOfWomenCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839() +
//                        genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064();
//                int sumMaleAndFemaleCases = sumOfMaleCases + sumOfWomenCases;
//                double percentageOfMale= ((double) sumOfMaleCases / (double) sumMaleAndFemaleCases) * 100;
//                double percentageOfFemale= ((double) sumOfWomenCases / (double) sumMaleAndFemaleCases) * 100;
//                List<Integer> test1 = Arrays.asList((int) percentageOfMale, (int) percentageOfFemale);
//                List<Double> decimalsOfPercentages = Arrays.asList(percentageOfMale - (int) percentageOfMale, percentageOfFemale - (int) percentageOfFemale);
//                if(decimalsOfPercentages.get(0) > decimalsOfPercentages.get(1)){
//                    ageDistributionInDatabase.setMalePercentageAgeDistribution(test1.get(0) + 1);
//                    ageDistributionInDatabase.setFemalePercentageAgeDistribution(100 - ageDistributionInDatabase.getMalePercentageAgeDistribution());
//
//                }else if(decimalsOfPercentages.get(0) < decimalsOfPercentages.get(1)){
//                    ageDistributionInDatabase.setFemalePercentageAgeDistribution(test1.get(1) + 1);
//                    ageDistributionInDatabase.setMalePercentageAgeDistribution(100 - ageDistributionInDatabase.getFemalePercentageAgeDistribution());
//                }else{
//                    ageDistributionInDatabase.setFemalePercentageAgeDistribution(50);
//                    ageDistributionInDatabase.setMalePercentageAgeDistribution(50);
//
//                }
//                genderAgeRepository.save(ageDistributionInDatabase);
//
//            }else{
//            GenderAgeDistribution newAgeDistribution = new GenderAgeDistribution();
//            Cases maleCases = new Cases();
//            Critical maleCritical = new Critical();
//            Deaths maleDeaths = new Deaths();
//            Cases femaleCases = new Cases();
//            Critical femaleCritical = new Critical();
//            Deaths femaleDeaths = new Deaths();
//            Females newFemale = new Females();
//            Males newMale = new Males();
//            maleCases.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017());
//            maleCases.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65());
//            maleCases.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064());
//            maleCases.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839());
//            maleCases.setMales(newMale);
//
//            maleCritical.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_017());
//            maleCritical.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_65());
//            maleCritical.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_4064());
//            maleCritical.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_1839());
//            maleCritical.setMales(newMale);
//
//            maleDeaths.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_017());
//            maleDeaths.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_65());
//            maleDeaths.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_4064());
//            maleDeaths.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_1839());
//            maleDeaths.setMales(newMale);
//
//            femaleCases.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017());
//            femaleCases.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65());
//            femaleCases.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064());
//            femaleCases.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839());
//            femaleCases.setFemales(newFemale);
//
//            femaleCritical.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_017());
//            femaleCritical.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_65());
//            femaleCritical.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_4064());
//            femaleCritical.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_1839());
//            femaleCritical.setFemales(newFemale);
//
//            femaleDeaths.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_017());
//            femaleDeaths.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_65());
//            femaleDeaths.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_4064());
//            femaleDeaths.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_1839());
//            femaleDeaths.setFemales(newFemale);
//
//
//
//            newFemale.setCases(femaleCases);
//            newFemale.setCritical(femaleCritical);
//            newFemale.setDeaths(femaleDeaths);
//            newFemale.setGenderAgeDistribution(newAgeDistribution);
//
//
//            newMale.setCases(maleCases);
//            newMale.setCritical(maleCritical);
//            newMale.setDeaths(maleDeaths);
//            newMale.setGenderAgeDistribution(newAgeDistribution);
//
//            newAgeDistribution.setFemales(newFemale);
//            newAgeDistribution.setMales(newMale);
//
//            newAgeDistribution.setUpdateAt(genderAgeDistributionHistory.getUpdated());
//            int sumOfMaleCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839() +
//                    genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064();
//            int sumOfWomenCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839() +
//                        genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064();
//            int sumMaleAndFemaleCases = sumOfMaleCases + sumOfWomenCases;
//            double percentageOfMale= ((double) sumOfMaleCases / (double) sumMaleAndFemaleCases) * 100;
//            double percentageOfFemale= ((double) sumOfWomenCases / (double) sumMaleAndFemaleCases) * 100;
//            List<Integer> test1 = Arrays.asList((int) percentageOfMale, (int) percentageOfFemale);
//            List<Double> decimalsOfPercentages = Arrays.asList(percentageOfMale - (int) percentageOfMale, percentageOfFemale - (int) percentageOfFemale);
//            if(decimalsOfPercentages.get(0) > decimalsOfPercentages.get(1)){
//                newAgeDistribution.setMalePercentageAgeDistribution(test1.get(0) + 1);
//                newAgeDistribution.setFemalePercentageAgeDistribution(100 - newAgeDistribution.getMalePercentageAgeDistribution());
//
//            }else if(decimalsOfPercentages.get(0) < decimalsOfPercentages.get(1)){
//                newAgeDistribution.setFemalePercentageAgeDistribution(test1.get(1) + 1);
//                newAgeDistribution.setMalePercentageAgeDistribution(100 - newAgeDistribution.getFemalePercentageAgeDistribution());
//            }else{
//                newAgeDistribution.setFemalePercentageAgeDistribution(50);
//                newAgeDistribution.setMalePercentageAgeDistribution(50);
//
//            }
//            genderAgeRepository.save(newAgeDistribution);
//        }
//        return genderAgeRepository.findAll().get(0);
//    }



    @Override
    public GenderAgeDistribution getGenderAgeDistributionHistory() {
        return genderAgeRepository.findAll().get(0);
    }

    public void fetchAndSaveGenderAgeDistributionHistory() {

        PlatformConfiguration platformConfiguration = platformConfigurationRepository.findByConfigurationVariable("GENDER_AGE_LAST_UPDATE");
        Root genderAgeDistributionHistory = new Root();
        genderAgeDistributionHistory = genderAgeDistributionApiController.getAgeDistributionHistory();
        List<GenderAgeDistribution> ageDistributionInDatabase = genderAgeRepository.findAll();
        if(ageDistributionInDatabase.size() > 0){
            if(platformConfiguration!= null){
                if(platformConfiguration.getDateValue() != null){
                    saveGenderAgeDistribution(ageDistributionInDatabase.get(0),genderAgeDistributionHistory, platformConfiguration);
                }
        }
        } else{
            if(platformConfiguration!= null){
                if(platformConfiguration.getDateValue() == null){
                    saveGenderAgeDistribution(null,genderAgeDistributionHistory, platformConfiguration);
                }
            }

        }
    }

    private void saveGenderAgeDistribution(GenderAgeDistribution ageDistributionInDatabase, Root genderAgeDistributionHistory, PlatformConfiguration platformConfiguration) {
        if(ageDistributionInDatabase != null && !platformConfiguration.getDateValue().equals(genderAgeDistributionHistory.getUpdated())){
            ageDistributionInDatabase.getFemales().getCases().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017());
            ageDistributionInDatabase.getFemales().getCases().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839());
            ageDistributionInDatabase.getFemales().getCases().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65());
            ageDistributionInDatabase.getFemales().getCases().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064());
            ageDistributionInDatabase.getFemales().getCritical().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_017());
            ageDistributionInDatabase.getFemales().getCritical().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_1839());
            ageDistributionInDatabase.getFemales().getCritical().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_65());
            ageDistributionInDatabase.getFemales().getCritical().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_4064());
            ageDistributionInDatabase.getFemales().getDeaths().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_017());
            ageDistributionInDatabase.getFemales().getDeaths().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_1839());
            ageDistributionInDatabase.getFemales().getDeaths().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_65());
            ageDistributionInDatabase.getFemales().getDeaths().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_4064());
            ageDistributionInDatabase.getMales().getCases().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017());
            ageDistributionInDatabase.getMales().getCases().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839());
            ageDistributionInDatabase.getMales().getCases().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65());
            ageDistributionInDatabase.getMales().getCases().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064());
            ageDistributionInDatabase.getMales().getCritical().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_017());
            ageDistributionInDatabase.getMales().getCritical().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_1839());
            ageDistributionInDatabase.getMales().getCritical().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_65());
            ageDistributionInDatabase.getMales().getCritical().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_4064());
            ageDistributionInDatabase.getMales().getDeaths().setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_017());
            ageDistributionInDatabase.getMales().getDeaths().setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_1839());
            ageDistributionInDatabase.getMales().getDeaths().setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_65());
            ageDistributionInDatabase.getMales().getDeaths().setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_4064());
            ageDistributionInDatabase.setUpdateAt(genderAgeDistributionHistory.getUpdated());

            int sumOfMaleCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839() +
                    genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064();
            int sumOfWomenCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839() +
                    genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064();
            int sumMaleAndFemaleCases = sumOfMaleCases + sumOfWomenCases;
            double percentageOfMale= ((double) sumOfMaleCases / (double) sumMaleAndFemaleCases) * 100;
            double percentageOfFemale= ((double) sumOfWomenCases / (double) sumMaleAndFemaleCases) * 100;
            List<Integer> test1 = Arrays.asList((int) percentageOfMale, (int) percentageOfFemale);
            List<Double> decimalsOfPercentages = Arrays.asList(percentageOfMale - (int) percentageOfMale, percentageOfFemale - (int) percentageOfFemale);
            if(decimalsOfPercentages.get(0) > decimalsOfPercentages.get(1)){
                ageDistributionInDatabase.setMalePercentageAgeDistribution(test1.get(0) + 1);
                ageDistributionInDatabase.setFemalePercentageAgeDistribution(100 - ageDistributionInDatabase.getMalePercentageAgeDistribution());

            }else if(decimalsOfPercentages.get(0) < decimalsOfPercentages.get(1)){
                ageDistributionInDatabase.setFemalePercentageAgeDistribution(test1.get(1) + 1);
                ageDistributionInDatabase.setMalePercentageAgeDistribution(100 - ageDistributionInDatabase.getFemalePercentageAgeDistribution());
            }else{
                ageDistributionInDatabase.setFemalePercentageAgeDistribution(50);
                ageDistributionInDatabase.setMalePercentageAgeDistribution(50);

            }
            platformConfiguration.setDateValue(genderAgeDistributionHistory.getUpdated());
            platformConfigurationRepository.save(platformConfiguration);
            genderAgeRepository.save(ageDistributionInDatabase);

        }else if(ageDistributionInDatabase == null){
            GenderAgeDistribution newAgeDistribution = new GenderAgeDistribution();
            Cases maleCases = new Cases();
            Critical maleCritical = new Critical();
            Deaths maleDeaths = new Deaths();
            Cases femaleCases = new Cases();
            Critical femaleCritical = new Critical();
            Deaths femaleDeaths = new Deaths();
            Females newFemale = new Females();
            Males newMale = new Males();
            maleCases.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017());
            maleCases.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65());
            maleCases.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064());
            maleCases.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839());
            maleCases.setMales(newMale);

            maleCritical.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_017());
            maleCritical.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_65());
            maleCritical.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_4064());
            maleCritical.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCritical().get_1839());
            maleCritical.setMales(newMale);

            maleDeaths.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_017());
            maleDeaths.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_65());
            maleDeaths.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_4064());
            maleDeaths.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getDeaths().get_1839());
            maleDeaths.setMales(newMale);

            femaleCases.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017());
            femaleCases.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65());
            femaleCases.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064());
            femaleCases.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839());
            femaleCases.setFemales(newFemale);

            femaleCritical.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_017());
            femaleCritical.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_65());
            femaleCritical.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_4064());
            femaleCritical.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCritical().get_1839());
            femaleCritical.setFemales(newFemale);

            femaleDeaths.setAge_0_17(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_017());
            femaleDeaths.setAge_65(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_65());
            femaleDeaths.setAge_40_64(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_4064());
            femaleDeaths.setAge_18_39(genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getDeaths().get_1839());
            femaleDeaths.setFemales(newFemale);



            newFemale.setCases(femaleCases);
            newFemale.setCritical(femaleCritical);
            newFemale.setDeaths(femaleDeaths);
            newFemale.setGenderAgeDistribution(newAgeDistribution);


            newMale.setCases(maleCases);
            newMale.setCritical(maleCritical);
            newMale.setDeaths(maleDeaths);
            newMale.setGenderAgeDistribution(newAgeDistribution);

            newAgeDistribution.setFemales(newFemale);
            newAgeDistribution.setMales(newMale);

            newAgeDistribution.setUpdateAt(genderAgeDistributionHistory.getUpdated());
            int sumOfMaleCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_1839() +
                    genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getMales().getCases().get_4064();
            int sumOfWomenCases = genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_017() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_1839() +
                    genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_65() + genderAgeDistributionHistory.getTotal_age_gender_distribution().getFemales().getCases().get_4064();
            int sumMaleAndFemaleCases = sumOfMaleCases + sumOfWomenCases;
            double percentageOfMale= ((double) sumOfMaleCases / (double) sumMaleAndFemaleCases) * 100;
            double percentageOfFemale= ((double) sumOfWomenCases / (double) sumMaleAndFemaleCases) * 100;
            List<Integer> test1 = Arrays.asList((int) percentageOfMale, (int) percentageOfFemale);
            List<Double> decimalsOfPercentages = Arrays.asList(percentageOfMale - (int) percentageOfMale, percentageOfFemale - (int) percentageOfFemale);
            if(decimalsOfPercentages.get(0) > decimalsOfPercentages.get(1)){
                newAgeDistribution.setMalePercentageAgeDistribution(test1.get(0) + 1);
                newAgeDistribution.setFemalePercentageAgeDistribution(100 - newAgeDistribution.getMalePercentageAgeDistribution());

            }else if(decimalsOfPercentages.get(0) < decimalsOfPercentages.get(1)){
                newAgeDistribution.setFemalePercentageAgeDistribution(test1.get(1) + 1);
                newAgeDistribution.setMalePercentageAgeDistribution(100 - newAgeDistribution.getFemalePercentageAgeDistribution());
            }else{
                newAgeDistribution.setFemalePercentageAgeDistribution(50);
                newAgeDistribution.setMalePercentageAgeDistribution(50);

            }
            platformConfiguration.setDateValue(genderAgeDistributionHistory.getUpdated());
            platformConfigurationRepository.save(platformConfiguration);
            genderAgeRepository.save(newAgeDistribution);
        }
    }



}
