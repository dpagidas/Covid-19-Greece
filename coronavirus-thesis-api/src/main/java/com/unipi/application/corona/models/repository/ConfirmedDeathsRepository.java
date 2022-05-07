package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.ConfirmedDeaths;
import com.unipi.application.corona.models.TotalTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ConfirmedDeathsRepository extends JpaRepository<ConfirmedDeaths, Long> {

    @Query(value="select * from confirmed_deaths where \"date\" <= :lastDate order by date desc limit 2", nativeQuery = true)
    List<ConfirmedDeaths> getLastTwoDatesByGivingDate(@Param("lastDate") Timestamp lastDate);



}
