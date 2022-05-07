package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.RegionCasesHistory;
import com.unipi.application.corona.models.projection.CasesListByRegionAndByDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RegionCasesHistoryRepository extends JpaRepository<RegionCasesHistory, Long> {

    @Query(value="select r.cases as Cases, rch.\"date\" as Date, a.area_gr as AreaGr, a.id as AreaId from regions r inner join region_cases_history rch on rch.id = r.region_case_history_id inner join areas a on a.id = r.area_id where date(rch.\"date\") = :date group by a.area_gr,r.cases,rch.\"date\", a.id", nativeQuery = true)
    List<CasesListByRegionAndByDate> getListCasesByRegionAndByDate(@Param("date") Timestamp date);


}
