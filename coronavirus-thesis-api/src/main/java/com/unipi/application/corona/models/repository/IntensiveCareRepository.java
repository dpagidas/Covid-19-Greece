package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.ConfirmedDeaths;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.models.TotalTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IntensiveCareRepository extends JpaRepository<IntensiveCare, Long> {

    @Query(value="select * from intensive_care where \"date\" = :lastDate order by date desc limit 1", nativeQuery = true)
    IntensiveCare getLastDateIntensiveCare(@Param("lastDate") Timestamp lastDate);



}
