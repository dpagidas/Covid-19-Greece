package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.Females;
import com.unipi.application.corona.models.GenderAgeDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface FemaleRepository extends JpaRepository<Females, Long> {


}
