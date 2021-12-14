package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.ConfirmedCases;
import com.unipi.application.corona.models.ConfirmedDeaths;
import com.unipi.application.corona.models.IntensiveCare;
import com.unipi.application.corona.models.TotalTests;
import com.unipi.application.corona.models.projection.CasesListByDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ConfirmedCasesRepository extends JpaRepository<ConfirmedCases, Long> {

    @Query(value="select * from confirmed_cases where \"date\" <= :lastDate order by date desc limit 2", nativeQuery = true)
    List<ConfirmedCases> getLastTwoDatesByGivingDate(@Param("lastDate") Timestamp lastDate);

    @Query(value="select * from confirmed_cases order by date desc limit 7", nativeQuery = true)
    List<ConfirmedCases> getLast7Days();


    @Query(value="select cc.\"date\" ,cc.confirmed - coalesce(lag(cc.confirmed) over (order by cc.id), 0) as confirmedCases, cd.deaths - coalesce(lag(cd.deaths) over (order by cd.id), 0) as confirmedDeaths , ic.intensive_care as intensiveCare, tt.tests - coalesce(lag(tt.tests) over (order by tt.id), 0) as tests , tt.rapid_tests - coalesce(lag(tt.rapid_tests) over (order by tt.id), 0) as rapidTests from confirmed_cases cc inner join confirmed_deaths cd  on date(cc.\"date\") = date(cd.\"date\") inner join intensive_care ic on date(cc.\"date\") = date(ic.\"date\") inner join total_tests tt on date(cc.\"date\") = date(tt.\"date\") WHERE ((CAST(:date as date) is null and CAST(:date1 as date) is null) or date(cc.\"date\") between :date and :date1) group by cc.\"date\", cd.deaths , cc.confirmed , ic.intensive_care, tt.tests , tt.rapid_tests, cc.id, cd.id, tt.id order by cc.\"date\"", nativeQuery = true)
    List<CasesListByDate> getListCasesDataByDate(@Param("date")  Timestamp date , @Param("date1")  Timestamp date1);



}
