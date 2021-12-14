package com.unipi.application.corona.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the PLATFORM_CONFIGURATION database table.
 *
 */
@Entity
@Table(name="PLATFORM_CONFIGURATION")
public class PlatformConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="configuration_variable", nullable=false)
    private String configurationVariable;

    @Column(name="variable_type", nullable=false)
    private String variableType;

    @Column(name="date_value")
    private Timestamp dateValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigurationVariable() {
        return configurationVariable;
    }

    public void setConfigurationVariable(String configurationVariable) {
        this.configurationVariable = configurationVariable;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public Timestamp getDateValue() {
        return dateValue;
    }

    public void setDateValue(Timestamp dateValue) {
        this.dateValue = dateValue;
    }
}
