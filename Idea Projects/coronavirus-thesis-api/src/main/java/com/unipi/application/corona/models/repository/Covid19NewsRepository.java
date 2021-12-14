package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.Covid19News;
import com.unipi.application.corona.models.PlatformConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Covid19NewsRepository extends JpaRepository<Covid19News, Long> {

    Page<Covid19News> findAll(Pageable pageanle);

}
