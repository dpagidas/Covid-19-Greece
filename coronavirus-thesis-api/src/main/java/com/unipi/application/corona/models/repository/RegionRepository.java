package com.unipi.application.corona.models.repository;


import com.unipi.application.corona.models.Region;
import com.unipi.application.corona.models.VaccinationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {


}
