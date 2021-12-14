package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.Cases;
import com.unipi.application.corona.models.Females;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Cases, Long> {


}
