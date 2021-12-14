package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.TotalTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface TotalTestsRepository extends JpaRepository<TotalTests, Long> {

    @Query(value="select * from total_tests where \"date\" <= :lastDate order by date desc limit 2", nativeQuery = true)
    List<TotalTests> getLastTwoDatesByGivingDate(@Param("lastDate") Timestamp lastDate);

}
