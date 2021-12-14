package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.PlatformConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Repository
public interface PlatformConfigurationRepository extends JpaRepository<PlatformConfiguration, Long> {

    PlatformConfiguration findByConfigurationVariable(String configurationValue);


}
