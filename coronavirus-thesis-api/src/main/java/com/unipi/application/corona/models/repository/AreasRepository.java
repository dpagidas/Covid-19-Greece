package com.unipi.application.corona.models.repository;

import com.unipi.application.corona.models.Areas;
import com.unipi.application.corona.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreasRepository extends JpaRepository<Areas, Long> {


    Areas findByAreaGr(String areaName);

}
