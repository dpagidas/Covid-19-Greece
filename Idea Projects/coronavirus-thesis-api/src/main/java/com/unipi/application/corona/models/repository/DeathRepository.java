package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.Critical;
import com.unipi.application.corona.models.Deaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeathRepository extends JpaRepository<Deaths, Long> {


}
