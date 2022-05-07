package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.Females;
import com.unipi.application.corona.models.Males;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaleRepository extends JpaRepository<Males, Long> {


}
