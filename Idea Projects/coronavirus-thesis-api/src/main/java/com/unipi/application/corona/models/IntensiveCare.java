package com.unipi.application.corona.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="intensive_care")
public class IntensiveCare implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="date")
    public Timestamp date;

    @Column(name="intensive_care")
    public Integer intensive_care;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getIntensive_care() {
        return intensive_care;
    }

    public void setIntensive_care(Integer intensive_care) {
        this.intensive_care = intensive_care;
    }
}
