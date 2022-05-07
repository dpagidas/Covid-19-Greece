package com.unipi.application.corona.models.repository;


import com.unipi.application.corona.dto.VaccinationInfoLastDateReturn;
import com.unipi.application.corona.models.*;
import com.unipi.application.corona.models.projection.VaccinationInfoLastDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VaccinationInfoRepository extends JpaRepository<VaccinationInfo, Long> {

    VaccinationInfo findByReferencedateAndAreas(Timestamp referenceDate, Areas areas);

    @Query(value="select sum(vi.totaldose2) as sumTotalDose2, sum(vi.totaldose3) as sumTotalDose3, sum(vi.totaldose1) as sumTotalDistinctPersons, sum(vi.total_vaccinations) as sumTotalVaccinations from vaccination_info vi where referencedate = :date", nativeQuery = true)
    VaccinationInfoLastDate fetchLastDateVaccinationResults(@Param("date") Timestamp date);

    @Query(value="select * from confirmed_cases where \"date\" <= :lastDate order by date desc limit 2", nativeQuery = true)
    List<VaccinationInfo> getLastTwoDatesVaccinationInfo(@Param("lastDate") Timestamp lastDate);

    @Query(value="select vi.referencedate, sum(vi.totaldose2) as sumTotalDose2, sum(vi.totaldose3) as sumTotalDose3,  sum(vi.totaldose1) as sumTotalDistinctPersons, sum(vi.total_vaccinations) as sumTotalVaccinations from vaccination_info vi WHERE ((CAST(:date as date) is null and CAST(:date1 as date) is null) or date(vi.referencedate) between :date and :date1) group by vi.referencedate order by vi.referencedate", nativeQuery = true)
    List<VaccinationInfoLastDate> fetchListOfDatesWithTotalResults(@Param("date")  Timestamp date , @Param("date1")  Timestamp date1);


    @Query(value="SELECT area_gr as AreaGr, a.id as AreaId ,vi.referencedate ,sum(vi.totaldose2) as sumTotalDose2,sum(vi.totaldose3) as sumTotalDose3, sum(vi.totaldose1) as sumTotalDistinctPersons, sum(vi.total_vaccinations) as sumTotalVaccinations\n" +
            "FROM vaccination_info vi\n" +
            "inner join areas a ON vi.area_id = a.id\n" +
            "WHERE ((CAST(:date as date) is null) or date(vi.referencedate) = :date)\n" +
            "group by a.area_gr, vi.referencedate, a.id\n" +
            "order by a.area_gr  ", nativeQuery = true)
    List<VaccinationInfoLastDate> fetchListOfVaccinationPerRegion(@Param("date")  Timestamp date);


}

