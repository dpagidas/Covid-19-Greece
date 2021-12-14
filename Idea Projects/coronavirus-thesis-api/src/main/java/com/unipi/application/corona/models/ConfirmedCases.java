package com.unipi.application.corona.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="confirmed_cases")
public class ConfirmedCases implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="date")
    public Timestamp date;

    @Column(name="confirmed")
    public Integer confirmed;

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

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }
}
